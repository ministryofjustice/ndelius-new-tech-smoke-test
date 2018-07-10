package uk.gov.justice.digital.hmpps.delius.paroleparom1report

import geb.spock.GebReportingSpec
import spock.lang.Stepwise
import uk.gov.justice.digital.hmpps.delius.dataload.ReportDataLoader
import uk.gov.justice.digital.hmpps.delius.pages.*

@Stepwise
class ParoleParom1ReportSpec_FullJourney extends GebReportingSpec {

    def setup() {
        ReportDataLoader.clear()
        to IndexPage
    }

    def 'Create report'() {

        given: 'I create a report'
        to ParoleParom1ReportPageFrame
        when: 'I select Start now'
        withFrame(newTechFrame, PP1LandingPage) {
            startNowButton.click()
        }
        then: 'I am at the Prisoner details page'
        withWindow("reportpopup") {
            report("popup")
            at(PP1PrisonerDetailsPage)
        }
    }
}
