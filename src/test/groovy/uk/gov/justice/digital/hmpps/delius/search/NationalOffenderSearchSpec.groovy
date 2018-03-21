package uk.gov.justice.digital.hmpps.delius.search

import geb.spock.GebReportingSpec
import spock.lang.Stepwise
import uk.gov.justice.digital.hmpps.delius.pages.IndexPage
import uk.gov.justice.digital.hmpps.delius.pages.NationalOffenderSearchPageFrame
import uk.gov.justice.digital.hmpps.delius.pages.NationalOffenderSearchPage

@Stepwise
class NationalOffenderSearchSpec extends GebReportingSpec {

    def setupSpec() {
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

}
