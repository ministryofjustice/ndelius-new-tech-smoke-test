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
        offenders[3] = offender( '/esdata/sam-jones.json' )
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

    @Unroll('#searchTerm should find offender with PNC #displayPnc')
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
        '2018/123456X'  | '2018/0123456X'
        '2018/123456x'  | '2018/0123456X'
        '18/0123456X'   | '2018/0123456X'
        '18/0123456x'   | '2018/0123456X'
        '18/123456X'    | '2018/0123456X'
        '18/123456x'    | '2018/0123456X'
    }

    @Unroll('#searchTerm should find offender with CRO #displayCro')
    def 'Searching for a record by CRO returns one result'(searchTerm, displayCro) {
        given: 'I am on the search page'
        to NationalOffenderSearchPageFrame

        when: 'I search for a specific CRO'
        withFrame(newTechFrame, NationalOffenderSearchPage) {
            enterSearchTerms(searchTerm)
        }

        then: 'I see an offender record and the full CRO is displayed'
        withFrame(newTechFrame, NationalOffenderSearchPage) {
            waitFor {hasResults}
            resultCount == 1
            offenders[0].contains(displayCro)
        }

        where:
        searchTerm     | displayCro
        'SF80/655108T' | 'SF80/655108T'
        'sf80/655108t' | 'SF80/655108T'
    }

    def 'Searching for a record by DoB returns one result'() {
        given: 'I am on the search page'
        to NationalOffenderSearchPageFrame

        when: 'I search for a specific DoB'
        withFrame(newTechFrame, NationalOffenderSearchPage) {
            enterSearchTerms('1978/1/6')
        }

        then: 'I see a single offender record'
        withFrame(newTechFrame, NationalOffenderSearchPage) {
            waitFor {hasResults}
            resultCount == 1
            offenders[0].contains('X00001')
            offenders[0].contains('06/01/1978')
        }
    }

    def 'Searching for a soft deleted record returns no results'() {
        given: 'I am on the search page'
        to NationalOffenderSearchPageFrame

        when: 'I search for a deleted offender by CRN'
        withFrame(newTechFrame, NationalOffenderSearchPage) {
            enterSearchTerms('X00066')
        }

        then: 'I see no offender records'
        withFrame(newTechFrame, NationalOffenderSearchPage) {
            waitFor {hasResults}
            resultCount == 0
        }
    }

    def 'Searching by single letter returns offenders with that first name initial'() {
        given: 'I am on the search page'
        to NationalOffenderSearchPageFrame

        when: 'I search for a first name initial'
        withFrame(newTechFrame, NationalOffenderSearchPage) {
            enterSearchTerms('J')
        }

        then: 'I see offender records that have that first name initial'
        withFrame(newTechFrame, NationalOffenderSearchPage) {
            waitFor {hasResults}
            resultCount == 2
            offenders[0].contains('X00002')
            offenders[0].contains('Jane')
            offenders[1].contains('X00001')
            offenders[1].contains('John')

        }
    }

    static def offender(String filename) {
        this.getClass().getResource(filename).text
    }
}
