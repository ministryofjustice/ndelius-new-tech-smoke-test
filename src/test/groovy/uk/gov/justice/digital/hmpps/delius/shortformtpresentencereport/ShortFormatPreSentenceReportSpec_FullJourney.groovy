package uk.gov.justice.digital.hmpps.delius.shortformtpresentencereport

import geb.driver.CachingDriverFactory
import geb.spock.GebReportingSpec
import org.openqa.selenium.Dimension
import spock.lang.Stepwise
import uk.gov.justice.digital.hmpps.delius.dataload.ReportDataLoader
import uk.gov.justice.digital.hmpps.delius.pages.*
import uk.gov.justice.digital.hmpps.delius.util.PDFReader

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
            getDriver().manage().window().setSize(new Dimension(830, 3500))
        }
        withWindow("reportpopup") {
            at(SFRPSOffenderDetailsPage)
            saveAndContinue.click()
        }

        withWindow("reportpopup") {
            at(SFRPSSentencingCourtDetailsPage)
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
            domesticAbuseInformationSourceCheckBox.click()
            otherInformationSourceCheckBox.click()
            fillOtherInformationDetailsWith("Other information details text")
            saveAndContinue.click()
        }

        withWindow("reportpopup") {
            at(SFRPSOffenceDetailsPage)
            fillMainOffenceWith("Main offence text")
            fillOtherOffencesWith("Other offences text")
            fillOffenceSummaryWith("Summary of offence text")
            saveAndContinue.click()
        }

        withWindow("reportpopup") {
            at(SFRPSOffenceAnalysisPage)
            fillOffenceAnalysisWith("Offence analysis text")
            fillPatternOfOffendingWith("Pattern of offending text")
            saveAndContinue.click()
        }

        withWindow("reportpopup") {
            at(SFRPSOffenderAssessmentPage)
            accommodationCheckBox.click()
            fillAccommodationWith("Accommodation text")
            employmentCheckBox.click()
            fillEmploymentWith("Employment text")
            financeCheckBox.click()
            fillFinanceWith("Finance text")
            relationshipsCheckBox.click()
            fillRelationshipsWith("Relationships text")
            substanceMisuseCheckBox.click()
            fillSubstanceMisuseWith("Substance misuse text")
            healthCheckBox.click()
            fillHealthWith("Health text")
            behaviourCheckBox.click()
            fillBehaviourWith("Behaviour text")
            otherCheckBox.click()
            fillOtherWith("Other text")
            setExperienceTraumaYes
            fillExperienceTraumaDetailsWith('Experience Trauma text')
            setCaringResponsibilitiesYes
            fillCaringResponsibilitiesDetailsWith('Caring Responsibilities text')
            saveAndContinue.click()
        }

        withWindow("reportpopup") {
            at(SFRPSRiskAssessmentPage)
            fillLikelihoodOfReOffendingWith("Likelihood of re-offending text")
            fillRiskOfSeriousHarmWith("Risk of serious harm text")
            setPreviousSupervisionResponseGood
            fillAdditionalPreviousSupervisionWith("Additional previous supervision text")
            saveAndContinue.click()
        }

        withWindow("reportpopup") {
            at(SFRPSConclusionPage)
            fillProposalWith("Proposed sentence text")
            saveAndContinue.click()
        }

        withWindow("reportpopup") {
            at(SFRPSCheckYourReportPage)
            signYourReportButton.click()
        }

        withWindow("reportpopup") {
            at(SFRPSSignYourReportPage)
            fillReportAuthorWith("Report author text")
            fillOfficeWith("Office text")
            fillCourtOfficePhoneNumberWith("01999 123456")
            fillCounterSignatureWith("Counter signature text")
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
            assert address == "V/lG6VLFD0oe0ATA89ibXvNOb0NYY89IPNYpZ5aF/c30hkEudqZ6d4IYWQwK5BTDkGS1TCKmWW0yJmzEKblMB/W8snR+3rxG89hNe/+pXV4="
            assert crn == "v5LH8B7tJKI7fEc9uM76SQ=="
            assert pnc == "gHYQKWx6gGbP7qo+wsQf6w=="

            assert court == "h7MBpp9IR8kIdeTf8grydw=="
            assert dateOfHearing == "o0Visp+OX0kRPpoKW4hx2g=="
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
            assert domesticAbuseInformationSource == "true"
            assert otherInformationSource == "true"
            assert otherInformationDetails.contains("Other information details text")

            assert mainOffence.contains("Main offence text")
            assert otherOffences.contains("Other offences text")
            assert offenceSummary.contains("Summary of offence text")

            assert offenceAnalysis.contains("Offence analysis text")
            assert patternOfOffending.contains("Pattern of offending text")

            assert issueAccommodation.contains("true")
            assert issueEmployment.contains("true")
            assert issueFinance.contains("true")
            assert issueRelationships.contains("true")
            assert issueSubstanceMisuse.contains("true")
            assert issueHealth.contains("true")
            assert issueBehaviour.contains("true")
            assert issueOther.contains("true")

            assert issueAccommodationDetails.contains("Accommodation text")
            assert issueEmploymentDetails.contains("Employment text")
            assert issueFinanceDetails.contains("Finance text")
            assert issueRelationshipsDetails.contains("Relationships text")
            assert issueSubstanceMisuseDetails.contains("Substance misuse text")
            assert issueHealthDetails.contains("Health text")
            assert issueBehaviourDetails.contains("Behaviour text")
            assert issueOtherDetails.contains("Other text")

            assert experienceTrauma.contains("yes")
            assert experienceTraumaDetails.contains("Experience Trauma text")
            assert caringResponsibilities.contains("yes")
            assert caringResponsibilitiesDetails.contains("Caring Responsibilities text")

            assert likelihoodOfReOffending.contains("Likelihood of re-offending text")
            assert riskOfSeriousHarm.contains("Risk of serious harm text")
            assert previousSupervisionResponse.contains("Good")
            assert additionalPreviousSupervision.contains("Additional previous supervision text")

            assert proposal.contains("Proposed sentence text")

            assert reportAuthor.contains("Report author text")
            assert office.contains("Office text")
            assert reportDate == new Date().format("dd/MM/yyyy")
            assert courtOfficePhoneNumber.contains("01999 123456")
            assert counterSignature.contains("Counter signature text")
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
        at SFPSRDocumentListPage

        and: 'My document is displayed'
        assert documentRows.size() == 1

        and: 'I download the PDF'
        def content = PDFReader.textContent(downloadBytes(firstDocumentViewLink.@href.replace('pdf', 'view_pdf')))
        content.contains 'Other information details text'
        content.contains 'Main offence text'
        content.contains 'Other offences text'
        content.contains 'Summary of offence text'
        content.contains 'Offence analysis text'
        content.contains 'Pattern of offending text'
        content.contains 'Accommodation text'
        content.contains 'Employment text'
        content.contains 'Finance text'
        content.contains 'Relationships text'
        content.contains 'Substance misuse text'
        content.contains 'Health text'
        content.contains 'Behaviour text'
        content.contains 'Other text'
        content.contains 'Experience Trauma text'
        content.contains 'Caring Responsibilities text'
        content.contains 'Likelihood of re-offending text'
        content.contains 'Risk of serious harm text'
        content.contains 'Additional previous supervision text'
        content.contains 'Proposed sentence text'
        content.contains 'Report author text'
        content.contains 'Office text'
        content.contains '01999 123456'
        content.contains 'Counter signature text'


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
