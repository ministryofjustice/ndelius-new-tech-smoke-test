package uk.gov.justice.digital.hmpps.delius.search

import geb.spock.GebReportingSpec
import spock.lang.Stepwise
import spock.lang.Unroll
import uk.gov.justice.digital.hmpps.delius.pages.IndexPage
import uk.gov.justice.digital.hmpps.delius.pages.NationalOffenderSearchPageFrame
import uk.gov.justice.digital.hmpps.delius.pages.NationalOffenderSearchPage

import static uk.gov.justice.digital.hmpps.delius.dataload.ESDataLoader.replace

@Stepwise
class NationalOffenderSearchSpec extends GebReportingSpec {

    def setupSpec() {
        def offenders = [:]
        offenders[1] = offender( '/esdata/john-smith.json' )
        offenders[2] = offender( '/esdata/jane-smith.json' )
        replace(offenders)
        to IndexPage
    }

    def 'Offender national Search presents search box'() {

        given: 'I am on the search page'
        to NationalOffenderSearchPageFrame

        when: 'I view the page'
        newTechFrame.isDisplayed()

        then: 'I see a search terms field'
        withFrame(newTechFrame, NationalOffenderSearchPage) {
            searchTerms.isDisplayed()
        }
    }

    def 'Searching for unique record returns one result'() {
        given: 'I am on the search page'
        to NationalOffenderSearchPageFrame

        when: 'I search for a specific CRN'
        withFrame(newTechFrame, NationalOffenderSearchPage) {
            enterSearchTerms('X00001')
        }

        then: 'I see a single offender record'
        withFrame(newTechFrame, NationalOffenderSearchPage) {
            waitFor {hasResults}
            resultCount == 1
            offenders[0].contains('X00001')
        }
    }

    def 'Searching for non-unique records returns multiple results'() {
        given: 'I am on the search page'
        to NationalOffenderSearchPageFrame

        when: 'I search for a matching surname'
        withFrame(newTechFrame, NationalOffenderSearchPage) {
            enterSearchTerms('smith')
        }

        then: 'I see multiple offender records'
        withFrame(newTechFrame, NationalOffenderSearchPage) {
            waitFor {hasResults}
            resultCount == 2
            offenders[0].contains('Smith')
            offenders[1].contains('Smith')
        }
    }

    def 'Searching for non-unique records with partial matches returns multiple results ordered'() {
        given: 'I am on the search page'
        to NationalOffenderSearchPageFrame

        when: 'I search for a matching surname and firstname'
        withFrame(newTechFrame, NationalOffenderSearchPage) {
            enterSearchTerms('john smith')
        }

        then: 'I see multiple offender records'
        withFrame(newTechFrame, NationalOffenderSearchPage) {
            waitFor {hasResults}
            resultCount == 2
            offenders[0].contains('John')
            offenders[0].contains('Smith')
            offenders[1].contains('Jane')
            offenders[1].contains('Smith')
        }
    }

    def 'Search presents suggestions for near misses'() {
        given: 'I am on the search page'
        to NationalOffenderSearchPageFrame

        when: 'I search for `smth`'
        withFrame(newTechFrame, NationalOffenderSearchPage) {
            enterSearchTerms('smth')
        }

        then: 'I see a suggestions link for `smith`'
        withFrame(newTechFrame, NationalOffenderSearchPage) {
            waitFor{suggestionsFor('smith')}
        }
    }

    @Unroll
    def 'Searching for a record by PNC returns one result'(searchTerm, displayPnc) {
        given: 'I am on the search page'
        to NationalOffenderSearchPageFrame

        when: 'I search for a specific PNC'
        withFrame(newTechFrame, NationalOffenderSearchPage) {
            enterSearchTerms(searchTerm)
        }

        then: 'I see an offender record and the full PNC is displayed'
        withFrame(newTechFrame, NationalOffenderSearchPage) {
            waitFor {hasResults}
            resultCount == 1
            offenders[0].contains(displayPnc)
        }

        where:
        searchTerm      | displayPnc
        '2018/0123456X' | '2018/0123456X'
        '2018/0123456x' | '2018/0123456X'
        '18/0123456X'   | '2018/0123456X'
        '18/0123456x'   | '2018/0123456X'
        '18/123456X'    | '2018/0123456X'
        '18/123456x'    | '2018/0123456X'
    }

    static def offender(String filename) {
        this.getClass().getResource(filename).text
    }
}
