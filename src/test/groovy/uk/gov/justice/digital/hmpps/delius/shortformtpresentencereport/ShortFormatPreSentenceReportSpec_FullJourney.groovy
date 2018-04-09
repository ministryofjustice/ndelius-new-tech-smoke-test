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
            fillAddressWith("22 Acacia Avenue")
            fillPncWith("2018/1234567A")
            saveAndContinue.click()
        }

        withFrame(newTechFrame, SFRPSSentencingCourtDetailsPage) {
            fillCourtWith("The court")
            fillLocalJusticeAreaWith("Cardiff")
            saveAndContinue.click()
        }

        withFrame(newTechFrame, SFRPSSourcesOfInformationPage) {
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
            fillMainOffenceWith("Main offence")
            fillOtherOffencesWith("Other offences")
            fillOffenceSummaryWith("Summary of offence")
            saveAndContinue.click()
        }

        withFrame(newTechFrame, SFRPSOffenceAnalysisPage) {
            fillOffenceAnalysisWith("Offence analysis")
            fillPatternOfOffendingWith("Pattern of offending")
            saveAndContinue.click()
        }

        withFrame(newTechFrame, SFRPSOffenderAssessmentPage) {
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
            fillLikelihoodOfReOffendingWith("Likelihood of re-offending")
            fillRiskOfSeriousHarmWith("Risk of serious harm")
            setPreviousSupervisionResponseGood
            fillAdditionalPreviousSupervisionWith("Additional previous supervision")
            saveAndContinue.click()
        }

        withFrame(newTechFrame, SFRPSConclusionPage) {
            fillProposalWith("Proposed sentence")
            saveAndContinue.click()
        }

        withFrame(newTechFrame, SFRPSCheckYourReportPage) {
            signYourReportButton.click()
        }

        withFrame(newTechFrame, SFRPSSignYourReportPage) {
            fillReportAuthorWith("Report author")
            fillOfficeWith("Office")
            fillCourtOfficePhoneNumberWith("01999 123456")
            fillCounterSignatureWith("Counter signature")
        }

        when: 'I navigate back to the first page'
        withFrame(newTechFrame, SFRPSSignYourReportPage) {
            offenderDetailsLink.click()
        }

        withFrame(newTechFrame, SFRPSOffenderDetailsPage) {
            saveAndContinue.click()
        }

        and: 'navigate back to the sign & date you report page'
        withFrame(newTechFrame, SFRPSSentencingCourtDetailsPage) {
            saveAndContinue.click()
        }

        withFrame(newTechFrame, SFRPSSourcesOfInformationPage) {
            saveAndContinue.click()
        }

        withFrame(newTechFrame, SFRPSOffenceDetailsPage) {
            saveAndContinue.click()
        }

        withFrame(newTechFrame, SFRPSOffenceAnalysisPage) {
            saveAndContinue.click()
        }

        withFrame(newTechFrame, SFRPSOffenderAssessmentPage) {
            saveAndContinue.click()
        }

        withFrame(newTechFrame, SFRPSRiskAssessmentPage) {
            saveAndContinue.click()
        }

        withFrame(newTechFrame, SFRPSConclusionPage) {
            saveAndContinue.click()
        }

        withFrame(newTechFrame, SFRPSCheckYourReportPage) {
            signYourReportButton.click()
        }

        withFrame(newTechFrame, SFRPSSignYourReportPage) {
            feedbackLink.click()
        }

        and: 'I visit the feedback page'
        withFrame(newTechFrame, SFRPSFeedbackPage) {
            fillFeedbackWith("It's brilliant")
            submitYourFeedbackButton.click()
        }

        then: 'All the form fields contain the data I entered'
        withFrame(newTechFrame, SFRPSSignYourReportPage) {
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

            submitButton.click()
        }

        and: 'I click view my document list'
        withFrame(newTechFrame, SFRPSCompletedPage) {
            documentListLink.click()
        }

        and: 'I return to the document list'
        at DocumentListPage

        and: 'My document is displayed'
        documentRows.size() == 1

        and: 'I can update my document'
        firstDocumentUpdateLink.click(ShortFormatPreSentenceReportUpdatePageFrame)

        and: 'I am on the sign you report page'
        withFrame(newTechFrame, SFRPSSignYourReportPage) {
            true
        }

    }
}
