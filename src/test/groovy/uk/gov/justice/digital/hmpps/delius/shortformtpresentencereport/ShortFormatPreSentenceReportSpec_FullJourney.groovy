package uk.gov.justice.digital.hmpps.delius.shortformtpresentencereport

import geb.driver.CachingDriverFactory
import geb.spock.GebReportingSpec
import org.openqa.selenium.Dimension
import spock.lang.Stepwise
import uk.gov.justice.digital.hmpps.delius.dataload.ReportDataLoader
import uk.gov.justice.digital.hmpps.delius.pages.*

@Stepwise
class ShortFormatPreSentenceReportSpec_FullJourney extends GebReportingSpec {

    def setup() {
        resetBrowser()
        CachingDriverFactory.clearCacheAndQuitDriver()
        ReportDataLoader.clear()
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
            assert visitedPages == "[1,2,3,4,5,6,7,8,9,10,11]"
            assert onBehalfOfUser == "92Q036CvVIRT/i428X3zpg=="
            assert entityId == "RRioaTyIHLGnja2CBw8hqg=="
            assert documentId != null
            assert pncSupplied == "true"
            assert addressSupplied == "true"

            assert name == "EmgDFTi7h7SjKyIeG6NiZQ=="
            assert dateOfBirth == "67jD2g1wdu4d2u2EL6FqOg=="
            assert age == "xZOZtUiYrNABLROslAT4sw=="
            assert address == "54QF/34mAxz/8pae5KfKCym488JZMHolQ3oZJXgHLDn2eNiymhED2Qcnsct1O+h2MbYjFEWnY1G/XTUpRpFSeRYDizGZi4nvn4DzFDAVPc8="
            assert crn == "v5LH8B7tJKI7fEc9uM76SQ=="
            assert pnc == "gHYQKWx6gGbP7qo+wsQf6w=="

            assert court == "QLoQsxswhfduyDSX4gvmsQ=="
            assert dateOfHearing == "igY1rhdHh6XNlTto+oNRSw=="
            assert localJusticeArea == "GoLwo2nCnYgh1QImL5p0Jw=="

            assert interviewInformationSource == "true"
            assert serviceRecordsInformationSource == "true"
            assert cpsSummaryInformationSource == "true"
            assert oasysAssessmentsInformationSource == "true"
            assert previousConvictionsInformationSource == "true"
            assert victimStatementInformationSource == "true"
            assert childrenServicesInformationSource == "true"
            assert policeInformationSource == "true"
            assert sentencingGuidelinesInformationSource == "true"
            assert otherInformationSource == "true"
            assert otherInformationDetails.contains("Other information details")

            assert mainOffence.contains("Main offence")
            assert otherOffences.contains("Other offences")
            assert offenceSummary.contains("Summary of offence")

            assert offenceAnalysis.contains("Offence analysis")
            assert patternOfOffending.contains("Pattern of offending")

            assert issueAccommodation.contains("true")
            assert issueEmployment.contains("true")
            assert issueFinance.contains("true")
            assert issueRelationships.contains("true")
            assert issueSubstanceMisuse.contains("true")
            assert issueHealth.contains("true")
            assert issueBehaviour.contains("true")
            assert issueOther.contains("true")

            assert issueAccommodationDetails.contains("Accommodation")
            assert issueEmploymentDetails.contains("Employment")
            assert issueFinanceDetails.contains("Finance")
            assert issueRelationshipsDetails.contains("Relationships")
            assert issueSubstanceMisuseDetails.contains("Substance misuse")
            assert issueHealthDetails.contains("Health")
            assert issueBehaviourDetails.contains("Behaviour")
            assert issueOtherDetails.contains("Other")

            assert experienceTrauma.contains("yes")
            assert experienceTraumaDetails.contains("Experience Trauma")
            assert caringResponsibilities.contains("yes")
            assert caringResponsibilitiesDetails.contains("Caring Responsibilities")

            assert likelihoodOfReOffending.contains("Likelihood of re-offending")
            assert riskOfSeriousHarm.contains("Risk of serious harm")
            assert previousSupervisionResponse.contains("Good")
            assert additionalPreviousSupervision.contains("Additional previous supervision")

            assert proposal.contains("Proposed sentence")
            assert consideredQualityDiversity.contains("yes")

            assert reportAuthor.contains("Report author")
            assert office.contains("Office")
            assert reportDate == new Date().format("dd/MM/yyyy")
            assert courtOfficePhoneNumber.contains("01999 123456")
            assert counterSignature.contains("Counter signature")
            assert startDate != null

            assert submitButton.click()

            return true
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
        assert documentRows.size() == 1

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
