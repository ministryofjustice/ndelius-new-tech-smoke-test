package uk.gov.justice.digital.hmpps.delius.shortformtpresentencereport

import geb.spock.GebReportingSpec
import spock.lang.Stepwise
import uk.gov.justice.digital.hmpps.delius.dataload.SFPSRDataLoader
import uk.gov.justice.digital.hmpps.delius.pages.*

@Stepwise
class ShortFormatPreSentenceReportSpec_FullJourney extends GebReportingSpec {

    def setup() {
        SFPSRDataLoader.clear()
        to IndexPage
    }

    def 'Create report'() {

        given: 'I create a report'
        to ShortFormatPreSentenceReportPageFrame
        withFrame(newTechFrame, SFRPSWelcomePage) {
            startNowButton.click()
        }

        withFrame(newTechFrame, SFRPSOffenderDetailsPage) {
            headingDisplayed
            fillAddressWith("22 Acacia Avenue")
            fillPncWith("2018/1234567A")
            saveAndContinue.click()
        }

        withFrame(newTechFrame, SFRPSSentencingCourtDetailsPage) {
            headingDisplayed
            fillCourtWith("The court")
            fillLocalJusticeAreaWith("Cardiff")
            saveAndContinue.click()
        }

        withFrame(newTechFrame, SFRPSSourcesOfInformationPage) {
            headingDisplayed
            interviewInformationSourceCheckBox.click()
            serviceRecordsInformationSourceCheckBox.click()
            cpsSummaryInformationSourceCheckBox.click()
            oasysAssessmentsInformationSourceCheckBox.click()
            previousConvictionsInformationSourceCheckBox.click()
            victimStatementInformationSourceCheckBox.click()
            childrenServicesInformationSourceCheckBox.click()
            policeInformationSourceCheckBox.click()
            sentencingGuidelinesInformationSourceCheckBox.click()
            otherInformationSourceCheckBox.click()
            fillOtherInformationDetailsWith("Other information details")
            saveAndContinue.click()
        }

        withFrame(newTechFrame, SFRPSOffenceDetailsPage) {
            headingDisplayed
            fillMainOffenceWith("Main offence")
            fillOtherOffencesWith("Other offences")
            fillOffenceSummaryWith("Summary of offence")
            saveAndContinue.click()
        }

        withFrame(newTechFrame, SFRPSOffenceAnalysisPage) {
            headingDisplayed
            fillOffenceAnalysisWith("Offence analysis")
            fillPatternOfOffendingWith("Pattern of offending")
            saveAndContinue.click()
        }

        withFrame(newTechFrame, SFRPSOffenderAssessmentPage) {
            headingDisplayed
            accommodationCheckBox.click()
            fillAccommodationWith("Accommodation")
            employmentCheckBox.click()
            fillEmploymentWith("Employment")
            financeCheckBox.click()
            fillFinanceWith("Finance")
            relationshipsCheckBox.click()
            fillRelationshipsWith("Relationships")
            substanceMisuseCheckBox.click()
            fillSubstanceMisuseWith("Substance misuse")
            healthCheckBox.click()
            fillHealthWith("Health")
            behaviourCheckBox.click()
            fillBehaviourWith("Behaviour")
            otherCheckBox.click()
            fillOtherWith("Other")
            saveAndContinue.click()
        }

        withFrame(newTechFrame, SFRPSRiskAssessmentPage) {
            headingDisplayed
            fillLikelihoodOfReOffendingWith("Likelihood of re-offending")
            fillRiskOfSeriousHarmWith("Risk of serious harm")
            setPreviousSupervisionResponseGood
            fillAdditionalPreviousSupervisionWith("Additional previous supervision")
            saveAndContinue.click()
        }

        withFrame(newTechFrame, SFRPSConclusionPage) {
            headingDisplayed
            fillProposalWith("Proposed sentence")
            saveAndContinue.click()
        }

        withFrame(newTechFrame, SFRPSCheckYourReportPage) {
            headingDisplayed
            signAndDateYourReportButton.click()
        }

        withFrame(newTechFrame, SFRPSSignAndDateYourReportPage) {
            headingDisplayed
            fillReportAuthorWith("Report author")
            fillOfficeWith("Office")
            fillCourtOfficePhoneNumberWith("01999 123456")
            fillCounterSignatureWith("Counter signature")
        }

        withFrame(newTechFrame, SFRPSOffenderDetailsPage) {
            offenderDetailsLink.click()
            headingDisplayed
            saveAndContinue.click()
        }

        withFrame(newTechFrame, SFRPSSentencingCourtDetailsPage) {
            headingDisplayed
            saveAndContinue.click()
        }

        withFrame(newTechFrame, SFRPSSourcesOfInformationPage) {
            headingDisplayed
            saveAndContinue.click()
        }

        withFrame(newTechFrame, SFRPSOffenceDetailsPage) {
            headingDisplayed
            saveAndContinue.click()
        }

        withFrame(newTechFrame, SFRPSOffenceAnalysisPage) {
            headingDisplayed
            saveAndContinue.click()
        }

        withFrame(newTechFrame, SFRPSOffenderAssessmentPage) {
            headingDisplayed
            saveAndContinue.click()
        }

        withFrame(newTechFrame, SFRPSRiskAssessmentPage) {
            headingDisplayed
            saveAndContinue.click()
        }

        withFrame(newTechFrame, SFRPSConclusionPage) {
            headingDisplayed
            saveAndContinue.click()
        }

        withFrame(newTechFrame, SFRPSCheckYourReportPage) {
            headingDisplayed
            signAndDateYourReportButton.click()
        }

        withFrame(newTechFrame, SFRPSSignAndDateYourReportPage) {
            headingDisplayed
            feedbackLink.click()
        }

        withFrame(newTechFrame, SFRPSFeedbackPage) {
            headingDisplayed
            fillFeedbackWith("It's brilliant")
            submitYourFeedbackButton.click()
        }

        withFrame(newTechFrame, SFRPSSignAndDateYourReportPage) {
            headingDisplayed

            assert $( "#visitedPages").value() == "[1,2,3,4,5,6,7,8,9,10,11]"
            assert $( "#onBehalfOfUser").value() == "92Q036CvVIRT/i428X3zpg=="
            assert $( "#entityId").value() == "RRioaTyIHLGnja2CBw8hqg=="
            assert $( "#documentId").value() != null
            assert $( "#pncSupplied").value() == "false"
            assert $( "#addressSupplied").value() == "false"

            assert $( "#name").value() == "xylkFTVA6GXA1GRZZxZ4MA=="
            assert $( "#dateOfBirth").value() == "twqjuUftRY5xaB556mJb6A=="
            assert $( "#age").value() == "RRioaTyIHLGnja2CBw8hqg=="
            assert $( "#address").value() == "ykrB30Y0rBHZonvbLA5mQlbcDC5KyebnSauCsmz6zmA="
            assert $( "#crn").value() == "v5LH8B7tJKI7fEc9uM76SQ=="
            assert $( "#pnc").value() == "8zu4lHB2HOhhelHmBCXTGw=="

            assert $( "#court").value() == "QLoQsxswhfduyDSX4gvmsQ=="
            assert $( "#dateOfHearing").value() == "igY1rhdHh6XNlTto+oNRSw=="
            assert $( "#localJusticeArea").value() == "GoLwo2nCnYgh1QImL5p0Jw=="

            assert $( "#interviewInformationSource").value() == "true"
            assert $( "#serviceRecordsInformationSource").value() == "true"
            assert $( "#cpsSummaryInformationSource").value() == "true"
            assert $( "#oasysAssessmentsInformationSource").value() == "true"
            assert $( "#previousConvictionsInformationSource").value() == "true"
            assert $( "#victimStatementInformationSource").value() == "true"
            assert $( "#childrenServicesInformationSource").value() == "true"
            assert $( "#policeInformationSource").value() == "true"
            assert $( "#sentencingGuidelinesInformationSource").value() == "true"
            assert $( "#otherInformationSource").value() == "true"
            assert $( "#otherInformationDetails").value() == "Other information details"

            assert $( "#mainOffence").value() == "Main offence"
            assert $( "#otherOffences").value() == "Other offences"
            assert $( "#offenceSummary").value() == "Summary of offence"

            assert $( "#offenceAnalysis").value() == "Offence analysis"
            assert $( "#patternOfOffending").value() == "Pattern of offending"

            assert $( "#issueAccommodation").value() == "true"
            assert $( "#issueEmployment").value() == "true"
            assert $( "#issueFinance").value() == "true"
            assert $( "#issueRelationships").value() == "true"
            assert $( "#issueSubstanceMisuse").value() == "true"
            assert $( "#issueHealth").value() == "true"
            assert $( "#issueBehaviour").value() == "true"
            assert $( "#issueOther").value() == "true"

            assert $( "#issueAccommodationDetails").value() == "Accommodation"
            assert $( "#issueEmploymentDetails").value() == "Employment"
            assert $( "#issueFinanceDetails").value() == "Finance"
            assert $( "#issueRelationshipsDetails").value() == "Relationships"
            assert $( "#issueSubstanceMisuseDetails").value() == "Substance misuse"
            assert $( "#issueHealthDetails").value() == "Health"
            assert $( "#issueBehaviourDetails").value() == "Behaviour"
            assert $( "#issueOtherDetails").value() == "Other"

            assert $( "#likelihoodOfReOffending").value() == "Likelihood of re-offending"
            assert $( "#riskOfSeriousHarm").value() == "Risk of serious harm"
            assert $( "#previousSupervisionResponse").value() == "Good"
            assert $( "#additionalPreviousSupervision").value() == "Additional previous supervision"

            assert $( "#proposal").value() == "Proposed sentence"

            assert $( "#reportAuthor").value() == "Report author"
            assert $( "#office").value() == "Office"
            assert $( "#reportDate").value() == new Date().format("dd/MM/yyyy")
            assert $( "#courtOfficePhoneNumber").value() == "01999 123456"
            assert $( "#counterSignature").value() == "Counter signature"
            assert $( "#startDate").value() != null
        }
    }
}
