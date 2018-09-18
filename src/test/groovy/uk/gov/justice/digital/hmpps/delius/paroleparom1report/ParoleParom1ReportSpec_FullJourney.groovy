package uk.gov.justice.digital.hmpps.delius.paroleparom1report

import geb.driver.CachingDriverFactory
import geb.spock.GebReportingSpec
import org.openqa.selenium.Dimension
import spock.lang.Stepwise
import uk.gov.justice.digital.hmpps.delius.dataload.ReportDataLoader
import uk.gov.justice.digital.hmpps.delius.pages.*
import uk.gov.justice.digital.hmpps.delius.util.PDFReader

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
        withWindow("reportpopup") {
            // make popup big enough so no inputs are below the footer
            getDriver().manage().window().setSize(new Dimension(830, 3500))
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
            fillVictimsImpactDetailsWith("Victims impact detail text")
            setVictimsSubmitVPSYes()
            setVictimsEngagedInVCSYes()
            fillVictimsVLOContactDatesWith("30/03/2018")
            saveAndContinue.click()
        }
        and: 'I complete the OPD pathway page'
        withWindow("reportpopup") {
            at(PP1OPDPathwayPage)
            setOPDPathwayServicesYes()
            saveAndContinue.click()
        }
        and: 'I complete the Behaviour in prison page'
        withWindow("reportpopup") {
            at(PP1BehaviourInPrisonPage)
            fillBehaviourDetailWith("Prisoner behaviour in prison text")
            fillRotlSummaryWith("RoTL summary text")
            saveAndContinue.click()
        }
        and: 'I complete the Interventions page'
        withWindow("reportpopup") {
            at(PP1InterventionsPage)
            fillInterventionsDetailWith("Interventions detail text")
            fillInterventionsSummaryWith("interventions summary text")
            saveAndContinue.click()
        }
        and: 'I complete the Current sentence plan page'
        withWindow("reportpopup") {
            at(PP1CurrentSentencePlanPage)
            fillSentencePlanWith("Current sentence plan detail text")
            saveAndContinue.click()
        }

        and: 'I complete the MAPPA page'
        withWindow("reportpopup") {
            at(PP1MappaPage)
            saveAndContinue.click()
        }
        and: 'I complete the Current risk assessment page'
        withWindow("reportpopup") {
            at(PP1CurrentRiskAssessmentPage)
            saveAndContinue.click()
        }
        and: 'I complete the RoSH community page'
        withWindow("reportpopup") {
            at(PP1RoshCommunityPage)
            setRoshCommunityPublicLow()
            setRoshCommunityKnownAdultMedium()
            setRoshCommunityChildrenHigh()
            setRoshCommunityPrisonersVeryHigh()
            setRoshCommunityStaffLow()
            saveAndContinue.click()
        }
        and: 'I complete the RoSH custody page'
        withWindow("reportpopup") {
            at(PP1RoshCustodyPage)
            saveAndContinue.click()
        }
        and: 'I complete the Risk to the prisoner page'
        withWindow("reportpopup") {
            at(PP1RiskToPrisonerPage)
            setSelfHarmCommunityYes()
            setSelfHarmCustodyYes()
            setOthersHarmCommunityYes()
            setOthersHarmCustodyYes()
            saveAndContinue.click()
        }

        and: 'I complete the RoSH analysis page'
        withWindow("reportpopup") {
            at(PP1RoshAnalysisPage)
            fillNatureOfRisk("Nature of risk detail text")
            fillIncreaseFactors("Increase factors detail text")
            fillDecreaseFactors("Decrease factors detail text")
            fillLikelihoodFurtherOffending("Likelihood of reoffending detail text")
            setRiskOfAbscondingYes()
            fillRiskOfAbscondingDetails("Risk of absconding detail text")
            saveAndContinue.click()
        }

        and: 'I complete the Risk Management Plan (RMP) page'
        withWindow("reportpopup") {
            at(PP1RiskManagementPlanPage)

            fillAgencies("Agencies text")
            fillSupport("Support text")
            fillControl("Control text")
            fillRiskMeasures("Risk Measures text")
            fillAgencyActions("Agency Actions text")
            fillAdditionalConditions("Additional conditions text")
            fillLevelOfContact("Level of contact text")
            fillContingencyPlan("Contingency plan text")

            saveAndContinue.click()
        }

        and: 'I complete the Resettlement plan for release page'
        withWindow("reportpopup") {
            at(PP1ResettlementPlanPage)

            setResettlementplanYes()
            fillResettlementPlanDetail("Resettlement plan detail text")

            saveAndContinue.click()
        }

        and: 'I complete the Supervision plan for release page'
        withWindow("reportpopup") {
            at(PP1SupervisionPlanPage)
            setSupervisionPlanRequiredYes()
            fillSupervisionPlanDetailWith "supervision plan detail text"
            saveAndContinue.click()
        }

        and: 'I complete the Recommendation page'
        withWindow("reportpopup") {
            at(PP1RecommendationPage)
            fillRecommendationWith "recommendation text"
            saveAndContinue.click()
        }

        and: 'I complete the Oral hearing'
        withWindow("reportpopup") {
            at(PP1OralHearingPage)
            fillOralHearingWith "oral hearing text"
            saveAndContinue.click()
        }

        and: 'I complete the Sources'
        withWindow("reportpopup") {
            at(PP1SourcesPage)
            clickSourcesPreviousConvictions()
            clickSourcesCPSDocuments()
            clickSourcesJudgesComments()
            clickSourcesParoleDossier()
            clickSourcesPreviousParoleReports()
            clickSourcesPreSentenceReport()
            clickSourcesProbationCaseRecord()
            clickSourcesOther()
            fillSourcesOtherDetailWith "sources other detail text"
            fillSourcesAssessmentListWith "sources assessment list text"
            setSourceLimitationsYes()
            fillSourceLimitationsDetailWith "source limitations detail text"
            saveAndContinue.click()
        }


        then: 'I am on the check your report page'
        withWindow("reportpopup") {
            at(PP1CheckYourReportPage)
            assert prisonerContactDetail.contains("Prisoner contact detail text")
            assert prisonerContactFamilyDetail.contains("Prisoner contact family detail text")
            assert prisonerContactAgenciesDetail.contains("Prisoner contact agencies detail text")

            assert victimsImpactDetails.contains("Victims impact detail text")
            assert victimsVLOContactDate == "30/03/2018"
            assert victimsVLOContactDate_day == "30"
            assert victimsVLOContactDate_month == "03"
            assert victimsVLOContactDate_year == "2018"
            assert victimsEngagedInVCS == "yes"
            assert victimsSubmitVPS == "yes"

            assert consideredForOPDPathwayServices == "yes"

            assert behaviourDetail.contains("Prisoner behaviour in prison text")
            assert rotlSummary.contains("RoTL summary text")

            assert interventionsDetail.contains("Interventions detail text")
            assert interventionsSummary.contains("interventions summary text")

            assert sentencePlan.contains("Current sentence plan detail text")

            // Page 15 - RoSH analysis
            assert natureOfRisk.contains("Nature of risk detail text")
            assert increaseFactors.contains("Increase factors detail text")
            assert decreaseFactors.contains("Decrease factors detail text")
            assert likelihoodFurtherOffending.contains("Likelihood of reoffending detail text")
            assert riskOfAbsconding == "yes"
            assert riskOfAbscondingDetails.contains("Risk of absconding detail text")

            // Page 16 - Risk Management Plan (RMP)
            assert agencies.contains("Agencies text")
            assert support.contains("Support text")
            assert control.contains("Control text")
            assert riskMeasures.contains("Risk Measures text")
            assert agencyActions.contains("Agency Actions text")
            assert additionalConditions.contains("Additional conditions text")
            assert levelOfContact.contains("Level of contact text")
            assert contingencyPlan.contains("Contingency plan text")

            assert supervisionPlanRequired == "yes"
            assert supervisionPlanDetail.contains("supervision plan detail text")

            assert recommendation.contains("recommendation text")

            assert sourcesPreviousConvictions == "true"
            assert sourcesCPSDocuments == "true"
            assert sourcesJudgesComments == "true"
            assert sourcesParoleDossier == "true"
            assert sourcesPreviousParoleReports == "true"
            assert sourcesPreSentenceReport == "true"
            assert sourcesProbationCaseRecord == "true"
            assert sourcesOther == "true"
            assert sourcesOtherDetail.contains("sources other detail text")
            assert sourcesAssessmentList.contains("sources assessment list text")
            assert sourceLimitations == "yes"
            assert sourceLimitationsDetail.contains("source limitations detail text")

            true
        }
        and: 'I close the popup window'
        withWindow("reportpopup") {
            at(PP1CheckYourReportPage)
            saveDraftLink.click()
            at(PP1DraftPage)
            closeLink.click()
        }
        and: 'I click back to document list link '
        withFrame(newTechFrame, PP1LandingPage) {
            documentListLink.click()
        }

        and: 'I return to the document list'
        at PP1DocumentListPage

        and: 'My document is displayed'
        assert documentRows.size() == 1


        and: 'I download the PDF'
        def content = PDFReader.textContent(downloadBytes(firstDocumentViewLink.@href.replace('pdf', 'view_pdf')))
        content.contains 'Prisoner contact detail text'
        content.contains 'Prisoner contact family detail text'
        content.contains 'Prisoner contact agencies detail text'

        content.contains 'Victims impact detail text'
        content.contains "Victim Liaison Officer (VLO) contacted 30/03/2018"
        content.contains "Victim Contact Scheme (VCS) engagement Yes"
        content.contains "Victim Personal Statement (VPS) Yes"

        content.contains 'The prisoner has met the OPD screening criteria'

        content.contains 'Prisoner behaviour in prison text'
        content.contains 'RoTL summary text'

        content.contains 'Interventions detail text'
        content.contains 'interventions summary text'

        content.contains 'Current sentence plan detail text'

        content.contains 'Nature of risk detail text'
        content.contains 'Increase factors detail text'
        content.contains 'Decrease factors detail text'
        content.contains 'Likelihood of reoffending detail text'
        content.contains 'Risk of absconding detail text'

        content.contains 'Agencies text'
        content.contains 'Support text'
        content.contains 'Control text'
        content.contains 'Risk Measures text'
        content.contains 'Agency Actions text'
        content.contains 'Additional conditions text'
        content.contains 'Level of contact text'
        content.contains 'Contingency plan text'

        content.contains 'supervision plan detail text'

        content.contains 'recommendation text'

        content.contains "Previous convictions Yes"
        content.contains "CPS documents Yes"
        content.contains "Judges comments Yes"
        content.contains "Parole dossier Yes"
        content.contains "Probation case records Yes"
        content.contains "Pre-sentence report Yes"
        content.contains "Previous parole reports Yes"
        content.contains "Other Yes"
        content.contains 'sources other detail text'
        content.contains 'sources assessment list text'
        content.contains 'source limitations detail text'

    }
}
