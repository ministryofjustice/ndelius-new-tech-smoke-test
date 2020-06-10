package uk.gov.justice.digital.hmpps.delius.paroleparom1report

import geb.driver.CachingDriverFactory
import geb.spock.GebReportingSpec
import org.openqa.selenium.Dimension
import spock.lang.Stepwise
import uk.gov.justice.digital.hmpps.delius.dataload.ReportDataLoader
import uk.gov.justice.digital.hmpps.delius.pages.*
import uk.gov.justice.digital.hmpps.delius.util.PDFReader

import java.text.SimpleDateFormat

@Stepwise
class ParoleParom1ReportSpec_FullJourney extends GebReportingSpec {

    private static String getMonthNameAndYear(String date) {
        SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy")
        SimpleDateFormat outputFormat = new SimpleDateFormat("MMM yyyy", Locale.ENGLISH)
        Date parsedDate = inputFormat.parse(date)
        return outputFormat.format(parsedDate).toString()
    }

    private static String getDay(String date) {
        return date.substring(0, 2)
    }

    private static String getMonth(String date) {
        return date.substring(3, 5)
    }

    private static String getYear(String date) {
        return date.substring(6)
    }

    def yesterday = (new Date() - 1).format("dd/MM/yyyy")
    def tomorrow = (new Date() + 1).format("dd/MM/yyyy")

    def setup() {
        resetBrowser()
        CachingDriverFactory.clearCacheAndQuitDriver()
        ReportDataLoader.clear()
        to IndexPage
    }

    def 'Create draft report'() {

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

            setPrisonerDetailsPrisonersCategoryA

            fillPrisonerDetailSentenceWith "1 year"

            setPrisonerDetailsDeterminate
            setPrisonerDetailsDeterminateSentenceType
            fillPrisonerDetailsDeterminateReleaseDateDatesWith(tomorrow)

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
        and: 'I complete the RoSH at point of sentence page'
        withWindow("reportpopup") {
            at(PP1ROSHAtSentencePage)
            setRoshAtPointOfSentenceYes()
            fillRoshAtPointOfSentenceDateWith(yesterday.substring(3))
            setRoshAtPointOfSentencePublicLow()
            setRoshAtPointOfSentenceKnownAdultLow()
            setRoshAtPointOfSentenceChildrenLow()
            setRoshAtPointOfSentencePrisonersLow()
            setRoshAtPointOfSentenceStaffLow()
            fillRoshAtPointOfSentenceIndexWith("RoSH at point of sentence attitude to index offence text")
            fillRoshAtPointOfSentencePreviousWith("RoSH at point of sentence attitude to previous offending text")
            saveAndContinue.click()
        }
        and: 'I complete the Victims page'
        withWindow("reportpopup") {
            at(PP1VictimsPage)
            fillVictimsImpactDetailsWith("Victims impact detail text")
            setVictimsSubmitVPSYes()
            setVictimsEngagedInVCSYes()
            fillVictimsVLOContactDatesWith(yesterday)
            saveAndContinue.click()
        }
        and: 'I complete the OPD pathway page'
        withWindow("reportpopup") {
            at(PP1OPDPathwayPage)
            fillScreenedDateWith(yesterday)
            setOPDPathwayServicesYes()
            setOPDPathwayConsultationFormulationYes()
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
            setEligibleForMappaYes()
            fillScreenedDateWith(yesterday)
            setMappaCategory1()
            setMappaLevel2()
            saveAndContinue.click()
        }
        and: 'I complete the Current risk assessment page'
        withWindow("reportpopup") {
            at(PP1CurrentRiskAssessmentPage)
            setRiskAssessmentRSRScore("2.9")
            setRiskAssessmentOGRS3ReoffendingProbability("50")
            setRiskAssessmentOGPReoffendingProbability("67")
            setRiskAssessmentOVPReoffendingProbability("80")
            setRiskAssessmentMatrix2000AssessmentCompletedYes()
            setRiskAssessmentMatrix2000ScoreLow()
            setRiskAssessmentSpousalAssaultAssessmentCompletedYes()
            setRiskAssessmentSpousalAssaultScoreHigh()
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
            setRoshCustodyPublicLow()
            setRoshCustodyKnownAdultMedium()
            setRoshCustodyChildrenHigh()
            setRoshCustodyPrisonersVeryHigh()
            setRoshCustodyStaffLow()
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

            setRiskManagementPlanRequiredYes()
            fillCurrentSituation("Current situation text")
            fillSupervision("Supervision text")
            fillMonitoringControl("Monitoring / Control text")
            fillInterventionsTreatment("Interventions / Treatment text")
            fillVictimSafetyPlanning("Victim safety planning text")
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

        and: 'I am on the check your report page'
        withWindow("reportpopup") {
            at(PP1CheckYourReportPage)

            assert prisonerDetailsPrisonInstitution.contains("HMP Leeds")
            assert prisonerDetailsPrisonersFullName.contains("Sam Jones")
            assert prisonerDetailsPrisonNumber.contains("4815")
            assert prisonerDetailsNomisNumber.contains("G8678GG")
            assert prisonerDetailsPrisonersCategory.contains("a")
            assert prisonerDetailsOffence.contains("Obtaining a money transfer by deception x 3 - 03/09/2018<br>Dishonestly retaining a wrongful credit - 01/08/2018<br>Dishonest representation for obtaining benefit etc x 2")
            assert prisonerDetailsSentence.contains("1 year")
            assert prisonerDetailsSentenceType.contains("determinate")
            assert prisonerDetailsDeterminateSentenceType.contains("discretionaryConditionalRelease")
            assert prisonerDetailsParoleEligibilityDate == tomorrow
            assert prisonerDetailsParoleEligibilityDate_day == getDay(tomorrow)
            assert prisonerDetailsParoleEligibilityDate_month == getMonth(tomorrow)
            assert prisonerDetailsParoleEligibilityDate_year == getYear(tomorrow)

            assert prisonerContactDetail.contains("Prisoner contact detail text")
            assert prisonerContactFamilyDetail.contains("Prisoner contact family detail text")
            assert prisonerContactAgenciesDetail.contains("Prisoner contact agencies detail text")

            assert roshAtPosAssessmentCompleted == "yes"
            assert roshAtPosDate == getMonthNameAndYear(yesterday)
            assert roshAtPosPublic == "low"
            assert roshAtPosKnownAdult == "low"
            assert roshAtPosChildren == "low"
            assert roshAtPosPrisoners == "low"
            assert roshAtPosStaff == "low"
            assert roshAtPosAttitudeIndexOffence.contains("RoSH at point of sentence attitude to index offence text")
            assert roshAtPosAttitudePreviousOffending.contains("RoSH at point of sentence attitude to previous offending text")

            assert victimsImpactDetails.contains("Victims impact detail text")
            assert victimsVLOContactDate == yesterday
            assert victimsVLOContactDate_day == getDay(yesterday)
            assert victimsVLOContactDate_month == getMonth(yesterday)
            assert victimsVLOContactDate_year == getYear(yesterday)
            assert victimsEngagedInVCS == "yes"
            assert victimsSubmitVPS == "yes"

            assert consideredForOPDPathwayServices == "yes"
            assert opdScreenedDate == yesterday
            assert opdScreenedDate_day == getDay(yesterday)
            assert opdScreenedDate_month == getMonth(yesterday)
            assert opdScreenedDate_year == getYear(yesterday)
            assert consultationOrFormulation == "yes"

            assert behaviourDetail.contains("Prisoner behaviour in prison text")
            assert rotlSummary.contains("RoTL summary text")

            assert interventionsDetail.contains("Interventions detail text")
            assert interventionsSummary.contains("interventions summary text")

            assert riskAssessmentRSRScore  == "2.9"
            assert riskAssessmentOGRS3ReoffendingProbability  == "50"
            assert riskAssessmentOGPReoffendingProbability  == "67"
            assert riskAssessmentOVPReoffendingProbability  == "80"
            assert riskAssessmentMatrix2000AssessmentCompleted  == "yes"
            assert riskAssessmentMatrix2000Score  == "low"
            assert riskAssessmentSpousalAssaultAssessmentCompleted  == "yes"
            assert riskAssessmentSpousalAssaultScore  == "high"

            assert sentencePlan.contains("Current sentence plan detail text")

            // Page 10 - MAPPA
            assert eligibleForMappa == "yes"
            assert mappaScreenedDate == yesterday
            assert mappaScreenedDate_day == getDay(yesterday)
            assert mappaScreenedDate_month == getMonth(yesterday)
            assert mappaScreenedDate_year == getYear(yesterday)
            assert mappaCategory == "1"
            assert mappaLevel == "2"

            // Page 15 - RoSH analysis
            assert natureOfRisk.contains("Nature of risk detail text")
            assert increaseFactors.contains("Increase factors detail text")
            assert decreaseFactors.contains("Decrease factors detail text")
            assert likelihoodFurtherOffending.contains("Likelihood of reoffending detail text")
            assert riskOfAbsconding == "yes"
            assert riskOfAbscondingDetails.contains("Risk of absconding detail text")

            // Page 16 - Risk Management Plan (RMP)
            assert riskManagementPlanRequired == "yes"
            assert currentSituation.contains("Current situation text")
            assert supervision.contains("Supervision text")
            assert monitoringControl.contains("Monitoring / Control text")
            assert victimSafetyPlanning.contains("Victim safety planning  text")
            assert interventionsTreatment.contains("Interventions / Treatment text")
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

        then: 'I am on the check your report page'
        withWindow("reportpopup") {
            at(PP1CheckYourReportPage)
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

        content.contains 'HMP Leeds'
        content.contains 'Sam Jones'
        content.contains '4815'
        content.contains 'G8678GG'

        content.contains 'A'

        content.contains 'Obtaining a money transfer by deception x 3 - 03/09/2018'
        content.contains '1 year'

        content.contains 'Discretionary Conditional Release (DCR)'

        content.contains 'Prisoner contact detail text'
        content.contains 'Prisoner contact family detail text'
        content.contains 'Prisoner contact agencies detail text'

        content.contains "RoSH at point of sentence attitude to index offence text"
        content.contains "RoSH at point of sentence attitude to previous offending text"

        content.contains 'Victims impact detail text'
        content.contains "Victim Liaison Officer (VLO) contacted " + yesterday
        content.contains "Victim Contact Scheme (VCS) engagement Yes"
        content.contains "Victim Personal Statement (VPS) Yes"

        content.contains 'Offender Personality Disorder (OPD) pathway'
        content.contains 'Date of OPD screen'
        content.contains yesterday
        content.contains 'Consultation or formulation received'

        content.contains 'Prisoner behaviour in prison text'
        content.contains 'RoTL summary text'

        content.contains 'Interventions detail text'
        content.contains 'interventions summary text'

        content.contains 'Current sentence plan detail text'

        content.contains 'Multi Agency Public Protection Arrangements (MAPPA)'
        content.contains 'MAPPAQ completed'
        content.contains yesterday
        content.contains 'Prisoner\'s current MAPPA category'
        content.contains 'Prisoner\'s current MAPPA level'
        content.contains '1'
        content.contains '2'

        content.contains "RSR  Low (2.9)"
        content.contains "OGRS3 (year 2)  Medium (50)"
        content.contains "OGP (year 2)  High (67)"
        content.contains "OVP (year 2)  Very high (80)"
        content.contains "Risk matrix 2000 Low"
        content.contains "SARA High"

        content.contains 'Current RoSH'
        content.contains 'Public'
        content.contains 'Known adult'
        content.contains 'Children'
        content.contains 'Prisoners'
        content.contains 'Staff'
        content.contains 'Community Low Medium High Very high Low'
        content.contains 'Custody Low Medium High Very high Low'

        content.contains 'Nature of risk detail text'
        content.contains 'Increase factors detail text'
        content.contains 'Decrease factors detail text'
        content.contains 'Likelihood of reoffending detail text'
        content.contains 'Risk of absconding detail text'

        content.contains 'Current situation text'
        content.contains 'Supporting agencies text'
        content.contains 'Monitoring / Control text'
        content.contains 'Interventions / Treatment text'
        content.contains 'Victim safety planning text'
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

            setPrisonerDetailsPrisonersCategoryA

            fillPrisonerDetailSentenceWith "1 year"

            setPrisonerDetailsDeterminate
            setPrisonerDetailsDeterminateSentenceType
            fillPrisonerDetailsDeterminateReleaseDateDatesWith(tomorrow)

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
        and: 'I complete the RoSH at point of sentence page'
        withWindow("reportpopup") {
            at(PP1ROSHAtSentencePage)
            setRoshAtPointOfSentenceYes()
            fillRoshAtPointOfSentenceDateWith(yesterday.substring(3))
            setRoshAtPointOfSentencePublicLow()
            setRoshAtPointOfSentenceKnownAdultLow()
            setRoshAtPointOfSentenceChildrenLow()
            setRoshAtPointOfSentencePrisonersLow()
            setRoshAtPointOfSentenceStaffLow()
            fillRoshAtPointOfSentenceIndexWith("RoSH at point of sentence attitude to index offence text")
            fillRoshAtPointOfSentencePreviousWith("RoSH at point of sentence attitude to previous offending text")
            saveAndContinue.click()
        }
        and: 'I complete the Victims page'
        withWindow("reportpopup") {
            at(PP1VictimsPage)
            fillVictimsImpactDetailsWith("Victims impact detail text")
            setVictimsSubmitVPSYes()
            setVictimsEngagedInVCSYes()
            fillVictimsVLOContactDatesWith(yesterday)
            saveAndContinue.click()
        }
        and: 'I complete the OPD pathway page'
        withWindow("reportpopup") {
            at(PP1OPDPathwayPage)
            fillScreenedDateWith(yesterday)
            setOPDPathwayServicesYes()
            setOPDPathwayConsultationFormulationYes()
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
            setEligibleForMappaYes()
            fillScreenedDateWith(yesterday)
            setMappaCategory1()
            setMappaLevel2()
            saveAndContinue.click()
        }
        and: 'I complete the Current risk assessment page'
        withWindow("reportpopup") {
            at(PP1CurrentRiskAssessmentPage)
            setRiskAssessmentRSRScore("2.9")
            setRiskAssessmentOGRS3ReoffendingProbability("50")
            setRiskAssessmentOGPReoffendingProbability("67")
            setRiskAssessmentOVPReoffendingProbability("80")
            setRiskAssessmentMatrix2000AssessmentCompletedYes()
            setRiskAssessmentMatrix2000ScoreLow()
            setRiskAssessmentSpousalAssaultAssessmentCompletedYes()
            setRiskAssessmentSpousalAssaultScoreHigh()
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
            setRoshCustodyPublicLow()
            setRoshCustodyKnownAdultMedium()
            setRoshCustodyChildrenHigh()
            setRoshCustodyPrisonersVeryHigh()
            setRoshCustodyStaffLow()
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

            setRiskManagementPlanRequiredYes()
            fillCurrentSituation("Current situation text")
            fillSupervision("Supervision text")
            fillMonitoringControl("Monitoring / Control text")
            fillInterventionsTreatment("Interventions / Treatment text")
            fillVictimSafetyPlanning("Victim safety planning text")
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

        and: 'I am on the check your report page'
        withWindow("reportpopup") {
            at(PP1CheckYourReportPage)

            assert prisonerContactDetail.contains("Prisoner contact detail text")
            assert prisonerContactFamilyDetail.contains("Prisoner contact family detail text")
            assert prisonerContactAgenciesDetail.contains("Prisoner contact agencies detail text")

            assert roshAtPosAssessmentCompleted == "yes"
            assert roshAtPosDate == getMonthNameAndYear(yesterday)
            assert roshAtPosPublic == "low"
            assert roshAtPosKnownAdult == "low"
            assert roshAtPosChildren == "low"
            assert roshAtPosPrisoners == "low"
            assert roshAtPosStaff == "low"
            assert roshAtPosAttitudeIndexOffence.contains("RoSH at point of sentence attitude to index offence text")
            assert roshAtPosAttitudePreviousOffending.contains("RoSH at point of sentence attitude to previous offending text")

            assert victimsImpactDetails.contains("Victims impact detail text")
            assert victimsVLOContactDate == yesterday
            assert victimsVLOContactDate_day == getDay(yesterday)
            assert victimsVLOContactDate_month == getMonth(yesterday)
            assert victimsVLOContactDate_year == getYear(yesterday)
            assert victimsEngagedInVCS == "yes"
            assert victimsSubmitVPS == "yes"

            assert opdScreenedDate == yesterday
            assert opdScreenedDate_day == getDay(yesterday)
            assert opdScreenedDate_month == getMonth(yesterday)
            assert opdScreenedDate_year == getYear(yesterday)
            assert consideredForOPDPathwayServices == "yes"
            assert consultationOrFormulation == "yes"

            assert behaviourDetail.contains("Prisoner behaviour in prison text")
            assert rotlSummary.contains("RoTL summary text")

            assert interventionsDetail.contains("Interventions detail text")
            assert interventionsSummary.contains("interventions summary text")

            assert riskAssessmentRSRScore  == "2.9"
            assert riskAssessmentOGRS3ReoffendingProbability  == "50"
            assert riskAssessmentOGPReoffendingProbability  == "67"
            assert riskAssessmentOVPReoffendingProbability  == "80"
            assert riskAssessmentMatrix2000AssessmentCompleted  == "yes"
            assert riskAssessmentMatrix2000Score  == "low"
            assert riskAssessmentSpousalAssaultAssessmentCompleted  == "yes"
            assert riskAssessmentSpousalAssaultScore  == "high"

            assert sentencePlan.contains("Current sentence plan detail text")

            // Page 10 - MAPPA
            assert eligibleForMappa == "yes"
            assert mappaScreenedDate == yesterday
            assert mappaScreenedDate_day == getDay(yesterday)
            assert mappaScreenedDate_month == getMonth(yesterday)
            assert mappaScreenedDate_year == getYear(yesterday)
            assert mappaCategory == "1"
            assert mappaLevel == "2"

            // Page 15 - RoSH analysis
            assert natureOfRisk.contains("Nature of risk detail text")
            assert increaseFactors.contains("Increase factors detail text")
            assert decreaseFactors.contains("Decrease factors detail text")
            assert likelihoodFurtherOffending.contains("Likelihood of reoffending detail text")
            assert riskOfAbsconding == "yes"
            assert riskOfAbscondingDetails.contains("Risk of absconding detail text")

            // Page 16 - Risk Management Plan (RMP)
            assert riskManagementPlanRequired == "yes"
            assert currentSituation.contains("Current situation text")
            assert supervision.contains("Supervision text")
            assert monitoringControl.contains("Monitoring / Control text")
            assert victimSafetyPlanning.contains("Victim safety planning  text")
            assert interventionsTreatment.contains("Interventions / Treatment text")
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

            signReport.click()
        }

        and: 'I am on the Signature & date page'
        withWindow("reportpopup") {
            at(PP1SignaturePage)
            setSignatureName "Jane Doe"
            setSignatureDivision "Some division"
            setSignatureOfficeAddress "Some office somewhere"
            setSignatureEmail "jane.doe@nps.com"
            setSignatureTelephone "0123 456 789"
            setSignatureCounterName "Joe Bloggs"
            setSignatureCounterRole "SPO"
            fillSignatureDateWith(yesterday)

            assert signatureName.contains("Jane Doe")
            assert signatureDivision.contains("Some division")
            assert signatureOfficeAddress.contains("Some office somewhere")
            assert signatureEmail.contains("jane.doe@nps.com")
            assert signatureTelephone.contains("0123 456 789")
            assert signatureCounterName.contains("Joe Bloggs")
            assert signatureCounterRole.contains("SPO")
            assert signatureDate_day == getDay(yesterday)
            assert signatureDate_month == getMonth(yesterday)
            assert signatureDate_year == getYear(yesterday)

            true

            submitReport.click()
        }

        then: 'I am on the Report complete page and I close the popup window'
        withWindow("reportpopup") {
            at(PP1CompletedPage)
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

        content.contains 'HMP Leeds'
        content.contains 'Sam Jones'
        content.contains '4815'
        content.contains 'G8678GG'

        content.contains 'A'

        content.contains 'Obtaining a money transfer by deception x 3 - 03/09/2018'
        content.contains '1 year'

        content.contains 'Discretionary Conditional Release (DCR)'

        content.contains 'Prisoner contact detail text'
        content.contains 'Prisoner contact family detail text'
        content.contains 'Prisoner contact agencies detail text'

        content.contains "RoSH at point of sentence attitude to index offence text"
        content.contains "RoSH at point of sentence attitude to previous offending text"

        content.contains 'Victims impact detail text'
        content.contains "Victim Liaison Officer (VLO) contacted " + yesterday
        content.contains "Victim Contact Scheme (VCS) engagement Yes"
        content.contains "Victim Personal Statement (VPS) Yes"

        content.contains 'Offender Personality Disorder (OPD) pathway'
        content.contains 'Date of OPD screen'
        content.contains yesterday
        content.contains 'Consultation or formulation received'

        content.contains 'Prisoner behaviour in prison text'
        content.contains 'RoTL summary text'

        content.contains 'Interventions detail text'
        content.contains 'interventions summary text'

        content.contains 'Current sentence plan detail text'

        content.contains 'Multi Agency Public Protection Arrangements (MAPPA)'
        content.contains 'MAPPAQ completed'
        content.contains yesterday
        content.contains 'Prisoner\'s current MAPPA category'
        content.contains 'Prisoner\'s current MAPPA level'
        content.contains '1'
        content.contains '2'

        content.contains "RSR  Low (2.9)"
        content.contains "OGRS3 (year 2)  Medium (50)"
        content.contains "OGP (year 2)  High (67)"
        content.contains "OVP (year 2)  Very high (80)"
        content.contains "Risk matrix 2000 Low"
        content.contains "SARA High"

        content.contains 'Current RoSH'
        content.contains 'Public'
        content.contains 'Known adult'
        content.contains 'Children'
        content.contains 'Prisoners'
        content.contains 'Staff'
        content.contains 'Community Low Medium High Very high Low'
        content.contains 'Custody Low Medium High Very high Low'
        
        content.contains 'Nature of risk detail text'
        content.contains 'Increase factors detail text'
        content.contains 'Decrease factors detail text'
        content.contains 'Likelihood of reoffending detail text'
        content.contains 'Risk of absconding detail text'

        content.contains 'Current situation text'
        content.contains 'Supervision text'
        content.contains 'Monitoring / Control text'
        content.contains 'Interventions / Treatment text'
        content.contains 'Victim safety planning text'
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

        content.contains "Jane Doe"
        content.contains "Some division"
        content.contains "Some office somewhere"
        content.contains "jane.doe@nps.com"
        content.contains "0123 456 789"
        content.contains "Joe Bloggs"
        content.contains "SPO"
        content.contains yesterday
    }
}
