package uk.gov.justice.digital.hmpps.delius.search

import geb.spock.GebReportingSpec
import spock.lang.Stepwise
import uk.gov.justice.digital.hmpps.delius.pages.IndexPage
import uk.gov.justice.digital.hmpps.delius.pages.NationalOffenderSearchPage
import uk.gov.justice.digital.hmpps.delius.pages.NationalOffenderSearchPageFrame

import static uk.gov.justice.digital.hmpps.delius.dataload.ESDataLoader.replace

@Stepwise
class NationalOffenderSearchByProviderSpec extends GebReportingSpec {

    def setupSpec() {
        def offenders = [:]
        offenders[1] = offender( '/esdata/john-smith-n01.json' )
        offenders[2] = offender( '/esdata/john-smith-n02.json' )
        offenders[3] = offender( '/esdata/john-smith-n03.json' )
        offenders[4] = offender( '/esdata/jane-smith-n02.json' )
        replace(offenders)
        to IndexPage
    }
    def setup() {
        // clear any previous searches stored in local storage
        to NationalOffenderSearchPageFrame
        withFrame(newTechFrame, NationalOffenderSearchPage) {
            enterSearchTerms('')
            waitFor {resultCount == 0}
        }
    }


    def 'Searching in National Search mode finds all matches regardless of provider'() {
        given: 'I am on the search page'
        to NationalOffenderSearchPageFrame

        when: 'I search for a matching surname'
        withFrame(newTechFrame, NationalOffenderSearchPage) {
            enterSearchTerms('smith')
        }

        then: 'I see multiple offender records'
        withFrame(newTechFrame, NationalOffenderSearchPage) {
            waitFor {resultCount == 4}
        }
    }

    static def offender(String filename) {
        this.getClass().getResource(filename).text
    }
}
