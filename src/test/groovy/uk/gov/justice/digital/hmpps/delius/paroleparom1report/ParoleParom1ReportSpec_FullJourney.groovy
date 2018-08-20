package uk.gov.justice.digital.hmpps.delius.paroleparom1report

import geb.driver.CachingDriverFactory
import geb.spock.GebReportingSpec
import spock.lang.Stepwise
import uk.gov.justice.digital.hmpps.delius.dataload.ReportDataLoader
import uk.gov.justice.digital.hmpps.delius.pages.*

@Stepwise
class ParoleParom1ReportSpec_FullJourney extends GebReportingSpec {

    def setup() {
        resetBrowser()
        CachingDriverFactory.clearCacheAndQuitDriver()
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
        and: 'I complete the Prisoner details page'
        withWindow("reportpopup") {
            at(PP1PrisonerDetailsPage)
            saveAndContinue.click()
        }
        and: 'I complete the Prisoner contact page'
        withWindow("reportpopup") {
            at(PP1PrisonerContactPage)
            fillPrisonerContactDetailWith("Prisoner contact detail text")
            fillPrisonerContactFamilyDetailWith("Prisoner contact family detail text")
            fillPrisonerContactAgenciesDetailWith("Prisoner contact agencies detail text")
            saveAndContinue.click()
        }
        and: 'I complete the ROSH at point of sentence page'
        withWindow("reportpopup") {
            at(PP1ROSHAtSentencePage)
            saveAndContinue.click()
        }
        and: 'I complete the Victims page'
        withWindow("reportpopup") {
            at(PP1VictimsPage)
            saveAndContinue.click()
        }
        and: 'I complete the OPD pathway page'
        withWindow("reportpopup") {
            at(PP1OPDPathwayPage)
            saveAndContinue.click()
        }
        and: 'I complete the Behaviour in prison page'
        withWindow("reportpopup") {
            at(PP1BehaviourInPrisonPage)
            saveAndContinue.click()
        }
        and: 'I complete the Interventions page'
        withWindow("reportpopup") {
            at(PP1InterventionsPage)
            fillInterventionsDetailWith("Interventions detail text")
            fillInterventionsSummaryWith("interventions summary text")
            saveAndContinue.click()
        }
        then: 'I am on the current sentence plan page with all fields saved'
        withWindow("reportpopup") {
            at(PP1CurrentSentencePlanPage)
            assert prisonerContactDetail.contains("Prisoner contact detail text")
            assert prisonerContactFamilyDetail.contains("Prisoner contact family detail text")
            assert prisonerContactAgenciesDetail.contains("Prisoner contact agencies detail text")

            assert interventionsDetail.contains("Interventions detail text")
            assert interventionsSummary.contains("interventions summary text")

            true
        }
    }
}
