package uk.gov.justice.digital.hmpps.delius.shortformtpresentencereport

import geb.spock.GebReportingSpec
import org.openqa.selenium.Dimension
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

        withWindow("reportpopup") {
            // make popup big enough so no inputs are below the footer
            getDriver().manage().window().setSize(new Dimension(830, 3000))
        }
        withWindow("reportpopup") {
            at(SFRPSOffenderDetailsPage)
            fillAddressWith("22 Acacia Avenue")
            fillPncWith("2018/1234567A")
            saveAndContinue.click()
        }

        withWindow("reportpopup") {
            at(SFRPSSentencingCourtDetailsPage)
            fillCourtWith("The court")
            fillLocalJusticeAreaWith("Cardiff")
            saveAndContinue.click()
        }

        withWindow("reportpopup") {
            at(SFRPSSourcesOfInformationPage)
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

        withWindow("reportpopup") {
            at(SFRPSOffenceDetailsPage)
            fillMainOffenceWith("Main offence")
            fillOtherOffencesWith("Other offences")
            fillOffenceSummaryWith("Summary of offence")
            saveAndContinue.click()
        }

        withWindow("reportpopup") {
            at(SFRPSOffenceAnalysisPage)
            fillOffenceAnalysisWith("Offence analysis")
            fillPatternOfOffendingWith("Pattern of offending")
            saveAndContinue.click()
        }

        withWindow("reportpopup") {
            at(SFRPSOffenderAssessmentPage)
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
            setExperienceTraumaYes
            fillExperienceTraumaDetailsWith('Experience Trauma')
            setCaringResponsibilitiesYes
            fillCaringResponsibilitiesDetailsWith('Caring Responsibilities')
            saveAndContinue.click()
        }

        withWindow("reportpopup") {
            at(SFRPSRiskAssessmentPage)
            fillLikelihoodOfReOffendingWith("Likelihood of re-offending")
            fillRiskOfSeriousHarmWith("Risk of serious harm")
            setPreviousSupervisionResponseGood
            fillAdditionalPreviousSupervisionWith("Additional previous supervision")
            saveAndContinue.click()
        }

        withWindow("reportpopup") {
            at(SFRPSConclusionPage)
            fillProposalWith("Proposed sentence")
            setConsideredQualityDiversityYes
            saveAndContinue.click()
        }

        withWindow("reportpopup") {
            at(SFRPSCheckYourReportPage)
            signYourReportButton.click()
        }

        withWindow("reportpopup") {
            at(SFRPSSignYourReportPage)
            fillReportAuthorWith("Report author")
            fillOfficeWith("Office")
            fillCourtOfficePhoneNumberWith("01999 123456")
            fillCounterSignatureWith("Counter signature")
        }

        when: 'I navigate back to the first page'
        withWindow("reportpopup") {
            at(SFRPSSignYourReportPage)
            offenderDetailsLink.click()
        }

        withWindow("reportpopup") {
            at(SFRPSOffenderDetailsPage)
            saveAndContinue.click()
        }

        and: 'navigate back to the sign & date you report page'
        withWindow("reportpopup") {
            at(SFRPSSentencingCourtDetailsPage)
            saveAndContinue.click()
        }

        withWindow("reportpopup") {
            at(SFRPSSourcesOfInformationPage)
            saveAndContinue.click()
        }

        withWindow("reportpopup") {
            at(SFRPSOffenceDetailsPage)
            saveAndContinue.click()
        }

        withWindow("reportpopup") {
            at(SFRPSOffenceAnalysisPage)
            saveAndContinue.click()
        }

        withWindow("reportpopup") {
            at(SFRPSOffenderAssessmentPage)
            saveAndContinue.click()
        }

        withWindow("reportpopup") {
            at(SFRPSRiskAssessmentPage)
            saveAndContinue.click()
        }

        withWindow("reportpopup") {
            at(SFRPSConclusionPage)
            saveAndContinue.click()
        }

        withWindow("reportpopup") {
            at(SFRPSCheckYourReportPage)
            signYourReportButton.click()
        }

        withWindow("reportpopup") {
            at(SFRPSSignYourReportPage)
            feedbackLink.click()
        }

        and: 'I visit the feedback page'
        withWindow("reportpopup") {
            at(SFPSRFeedbackPage)
            fillFeedbackWith("It's brilliant")
            submitFeedbackButton.click()
        }

        then: 'All the form fields contain the data I entered'
        withWindow("reportpopup") {
            at(SFRPSSignYourReportPage)
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

            experienceTrauma == "yes"
            experienceTraumaDetails == "Experience Trauma"
            caringResponsibilities == "yes"
            caringResponsibilitiesDetails == "Caring Responsibilities"

            likelihoodOfReOffending == "Likelihood of re-offending"
            riskOfSeriousHarm == "Risk of serious harm"
            previousSupervisionResponse == "Good"
            additionalPreviousSupervision == "Additional previous supervision"

            proposal == "Proposed sentence"
            consideredQualityDiversity == "yes"

            reportAuthor == "Report author"
            office == "Office"
            reportDate == new Date().format("dd/MM/yyyy")
            courtOfficePhoneNumber == "01999 123456"
            counterSignature == "Counter signature"
            startDate != null

            submitButton.click()
        }


        and: 'I click close window'
        withWindow("reportpopup") {
            at(SFRPSCompletedPage)
            closeLink.click()
        }
        and: 'I click back to document list link '
        withFrame(newTechFrame, SFRPSWelcomePage) {
            documentListLink.click()
        }

        and: 'I return to the document list'
        at DocumentListPage

        and: 'My document is displayed'
        documentRows.size() == 1

        and: 'I select the document to update'
        firstDocumentUpdateLink.click(ShortFormatPreSentenceReportUpdatePageFrame)

        and: 'I select the continue now button'
        withFrame(newTechFrame, SFRPSWelcomePage) {
            continueNowButton.click()
        }

        and: 'I am on the sign you report page'
        withWindow("reportpopup") {
            at(SFRPSSignYourReportPage)
        }

    }
}
