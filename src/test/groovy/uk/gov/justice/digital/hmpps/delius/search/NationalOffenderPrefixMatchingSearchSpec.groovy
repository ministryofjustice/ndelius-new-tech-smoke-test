package uk.gov.justice.digital.hmpps.delius.search

import geb.driver.CachingDriverFactory
import geb.spock.GebReportingSpec
import spock.lang.Stepwise
import spock.lang.Unroll
import uk.gov.justice.digital.hmpps.delius.pages.IndexPage
import uk.gov.justice.digital.hmpps.delius.pages.NationalOffenderSearchPage
import uk.gov.justice.digital.hmpps.delius.pages.NationalOffenderSearchPageFrame

import static uk.gov.justice.digital.hmpps.delius.dataload.ESDataLoader.hasLoaded
import static uk.gov.justice.digital.hmpps.delius.dataload.ESDataLoader.replace

class NationalOffenderPrefixMatchingSearchSpec extends GebReportingSpec {

    def setupSpec() {
        def offenders = [:]
        offenders[1] = offender( '/esdata/john-smith.json' )
        offenders[2] = offender( '/esdata/jane-smith.json' )
        offenders[3] = offender( '/esdata/sam-jones-deleted.json' )
        offenders[4] = offender( '/esdata/antonio-gramsci-n01.json' )
        offenders[5] = offender( '/esdata/antonio-gramsci-n02.json' )
        offenders[6] = offender( '/esdata/antonio-gramsci-n03.json' )
        offenders[7] = offender( '/esdata/anne-gramsci-n02.json' )
        offenders[8] = offender( '/esdata/antonio-gramsci-c20.json' )
        offenders[9] = offender( '/esdata/mark-white.json' )
        offenders[10] = offender( '/esdata/mark-whitehead.json' )
        offenders[11] = offender( '/esdata/mary-whitehead.json' )
        offenders[12] = offender( '/esdata/sarah-whitehead.json' )
        offenders[13] = offender( '/esdata/mark-brown.json' )
        offenders[14] = offender( '/esdata/mary-white.json' )

        replace(offenders)
        to IndexPage
        waitFor(10) {
            hasLoaded(offenders)
        }
    }

    def setup() {
        resetBrowser()
        CachingDriverFactory.clearCacheAndQuitDriver()
    }
    def 'Searching with initial of firstname with `Match all terms` set to Yes returns partial matching firstanme'() {
        given: 'I am on the search page'
        to NationalOffenderSearchPageFrame

        when: 'I enter partial firstnme and full surname'
        withFrame(newTechFrame, NationalOffenderSearchPage) {
            enterSearchTerms('m whitehead')
        }

        then: 'I see the results for matches of surname and those that partial match firstname'
        withFrame(newTechFrame, NationalOffenderSearchPage) {
            waitFor {resultCount == 2}
        }
    }


    def 'Searching with partial surname with `Match all terms` set to Yes returns matching surname'() {
        given: 'I am on the search page'
        to NationalOffenderSearchPageFrame

        when: 'I enter firstnme and partial surname'
        withFrame(newTechFrame, NationalOffenderSearchPage) {
            enterSearchTerms('mary white')
        }

        then: 'I see the results for matches of firstname and those that partial match surname'
        withFrame(newTechFrame, NationalOffenderSearchPage) {
            waitFor {resultCount == 2}
        }
    }

    def 'Searching with initial of firstname and partial surname and with `Match all terms` set to Yes returns matching partial surname and firstname'() {
        given: 'I am on the search page'
        to NationalOffenderSearchPageFrame

        when: 'I enter firstnme and partial surname'
        withFrame(newTechFrame, NationalOffenderSearchPage) {
            enterSearchTerms('m white')
        }

        then: 'I see the results for matches of firstname and those that partial match surname'
        withFrame(newTechFrame, NationalOffenderSearchPage) {
            waitFor {resultCount == 5}
        }
    }

    def 'Searching with initial of firstname, partial surname and dob  and with `Match all terms` set to Yes returns matching partial surname and firstname but only with matching dob'() {
        given: 'I am on the search page'
        to NationalOffenderSearchPageFrame

        when: 'I enter firstnme and partial surname'
        withFrame(newTechFrame, NationalOffenderSearchPage) {
            enterSearchTerms('m white 19/7/1965')
        }

        then: 'I see the results for matches of partial firstname and those that partial match surname restricted by matching date of birth'
        withFrame(newTechFrame, NationalOffenderSearchPage) {
            waitFor {resultCount == 2}
        }
    }

    def 'Searching with surname, firstname and partial street name finds match'() {
        given: 'I am on the search page'
        to NationalOffenderSearchPageFrame

        when: 'I enter firstnme, surname and partial street name'
        withFrame(newTechFrame, NationalOffenderSearchPage) {
            enterSearchTerms('mark brown white')
        }

        then: 'I see the results for matches of surname, firstname and partial matching street name'
        withFrame(newTechFrame, NationalOffenderSearchPage) {
            waitFor {resultCount == 1}
        }
    }

    def 'Searching with surname, firstname and partial town name finds match'() {
        given: 'I am on the search page'
        to NationalOffenderSearchPageFrame

        when: 'I enter firstnme, surname and partial town name'
        withFrame(newTechFrame, NationalOffenderSearchPage) {
            enterSearchTerms('mark brown sheff')
        }

        then: 'I see the results for matches of surname, firstname and partial matching town name'
        withFrame(newTechFrame, NationalOffenderSearchPage) {
            waitFor {resultCount == 1}
        }
    }



    static def offender(String filename) {
        this.getClass().getResource(filename).text
    }
}
