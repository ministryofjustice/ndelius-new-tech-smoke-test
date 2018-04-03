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

            visitedPages == "[1,2,3,4,5,6,7,8,9,10,11]"
            onBehalfOfUser == "92Q036CvVIRT/i428X3zpg=="
            entityId == "RRioaTyIHLGnja2CBw8hqg=="
            documentId != null
            pncSupplied == "false"
            addressSupplied == "false"

            name == "xylkFTVA6GXA1GRZZxZ4MA=="
            dateOfBirth == "twqjuUftRY5xaB556mJb6A=="
            age == "RRioaTyIHLGnja2CBw8hqg=="
            address == "ykrB30Y0rBHZonvbLA5mQlbcDC5KyebnSauCsmz6zmA="
            crn == "v5LH8B7tJKI7fEc9uM76SQ=="
            pnc == "8zu4lHB2HOhhelHmBCXTGw=="

            court == "QLoQsxswhfduyDSX4gvmsQ=="
            dateOfHearing == "igY1rhdHh6XNlTto+oNRSw=="
            localJusticeArea == "GoLwo2nCnYgh1QImL5p0Jw=="

            interviewInformationSource == "true"
            serviceRecordsInformationSource == "true"
            cpsSummaryInformationSource == "true"
            oasysAssessmentsInformationSource == "true"
            previousConvictionsInformationSource == "true"
            victimStatementInformationSource == "true"
            childrenServicesInformationSource == "true"
            policeInformationSource == "true"
            sentencingGuidelinesInformationSource == "true"
            otherInformationSource == "true"
            otherInformationDetails == "Other information details"

            mainOffence == "Main offence"
            otherOffences == "Other offences"
            offenceSummary == "Summary of offence"

            offenceAnalysis == "Offence analysis"
            patternOfOffending == "Pattern of offending"

            issueAccommodation == "true"
            issueEmployment == "true"
            issueFinance == "true"
            issueRelationships == "true"
            issueSubstanceMisuse == "true"
            issueHealth == "true"
            issueBehaviour == "true"
            issueOther == "true"

            issueAccommodationDetails == "Accommodation"
            issueEmploymentDetails == "Employment"
            issueFinanceDetails == "Finance"
            issueRelationshipsDetails == "Relationships"
            issueSubstanceMisuseDetails == "Substance misuse"
            issueHealthDetails == "Health"
            issueBehaviourDetails == "Behaviour"
            issueOtherDetails == "Other"

            likelihoodOfReOffending == "Likelihood of re-offending"
            riskOfSeriousHarm == "Risk of serious harm"
            previousSupervisionResponse == "Good"
            additionalPreviousSupervision == "Additional previous supervision"

            proposal == "Proposed sentence"

            reportAuthor == "Report author"
            office == "Office"
            reportDate == new Date().format("dd/MM/yyyy")
            courtOfficePhoneNumber == "01999 123456"
            counterSignature == "Counter signature"
            startDate != null
        }
    }
}
