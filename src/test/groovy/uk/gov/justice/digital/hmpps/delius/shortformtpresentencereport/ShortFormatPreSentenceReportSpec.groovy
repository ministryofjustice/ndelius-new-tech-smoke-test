package uk.gov.justice.digital.hmpps.delius.shortformtpresentencereport

import geb.spock.GebReportingSpec
import spock.lang.Stepwise
import uk.gov.justice.digital.hmpps.delius.dataload.SFPSRDataLoader
import uk.gov.justice.digital.hmpps.delius.pages.DocumentListPage
import uk.gov.justice.digital.hmpps.delius.pages.IndexPage
import uk.gov.justice.digital.hmpps.delius.pages.SFRPSDraftSavedPage
import uk.gov.justice.digital.hmpps.delius.pages.SFRPSOffenceAnalysisPage
import uk.gov.justice.digital.hmpps.delius.pages.SFRPSOffenceDetailsPage
import uk.gov.justice.digital.hmpps.delius.pages.SFRPSOffenderDetailsPage
import uk.gov.justice.digital.hmpps.delius.pages.SFRPSSentencingCourtDetailsPage
import uk.gov.justice.digital.hmpps.delius.pages.SFRPSSourcesOfInformationPage
import uk.gov.justice.digital.hmpps.delius.pages.SFRPSWelcomePage
import uk.gov.justice.digital.hmpps.delius.pages.ShortFormatPreSentenceReportPageFrame
import uk.gov.justice.digital.hmpps.delius.pages.ShortFormatPreSentenceReportUpdatePageFrame

@Stepwise
class ShortFormatPreSentenceReportSpec extends GebReportingSpec {

    def setup() {
        SFPSRDataLoader.clear()
        to IndexPage
    }

    def 'Welcome page is displayed'() {

        given: 'I am on the create Short Format Pre Sentence Report home page'
        to ShortFormatPreSentenceReportPageFrame

        when: 'I view the page'
        newTechFrame.isDisplayed()

        then: 'I see the Short Format Pre-sentence Report welcome page'
        withFrame(newTechFrame, SFRPSWelcomePage) {
            startNowButton.isDisplayed()
        }
    }

    def 'Can save a draft report'() {
        given: 'I am on the offender details page'
        to ShortFormatPreSentenceReportPageFrame
        withFrame(newTechFrame, SFRPSWelcomePage) {
            startNowButton.click()
        }

        when: 'I click the save draft link'
        withWindow("reportpopup") {
            at(SFRPSOffenderDetailsPage)
            saveDraftLink.click()
        }
        and: 'I click close window'
        withWindow("reportpopup") {
            at(SFRPSDraftSavedPage)
            closeLink.click()
        }
        and: 'I click back to document list link '
        withFrame(newTechFrame, SFRPSWelcomePage) {
            documentListLink.click()
        }
        then: 'I return to the document list'
        at DocumentListPage
        and: 'My document is displayed'
        documentRows.size() == 1
        and: 'I select the document to update'
        firstDocumentUpdateLink.click(ShortFormatPreSentenceReportUpdatePageFrame)
        and: 'I select the continue now button'
        withFrame(newTechFrame, SFRPSWelcomePage) {
            continueNowButton.click()
        }
        and: 'I am taken to the last page in the report I edited'
        withWindow("reportpopup") {
            at(SFRPSOffenderDetailsPage)
            saveDraftLink.isDisplayed()
        }

    }

    def 'Changes in popup should not be overwritten when Continuing from document list'() {
        given: 'I fill some details in the offence details page'
        to ShortFormatPreSentenceReportPageFrame
        withFrame(newTechFrame, SFRPSWelcomePage) {
            startNowButton.click()
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
            saveAndContinue.click()
        }
        withWindow("reportpopup") {
            at(SFRPSOffenceDetailsPage)
        }
        and: 'I go back to start page via the document list'
        to DocumentListPage
        firstDocumentUpdateLink.click(ShortFormatPreSentenceReportUpdatePageFrame)
        and: 'Fill in details in the popuop window'
        withWindow("reportpopup") {
            at(SFRPSOffenceDetailsPage)
            fillMainOffenceWith("Main offence")
            fillOtherOffencesWith("Other offences")
            fillOffenceSummaryWith("Summary of offence")
            saveAndContinue.click()
        }
        when: 'I click continue now'
        withFrame(newTechFrame, SFRPSWelcomePage) {
            continueNowButton.click()
        }
        then: 'I should be presented with the offence analysis page'
        withWindow("reportpopup") {
            report("popup")
            at(SFRPSOffenceAnalysisPage)
        }
    }
    def 'New report that is not continued when update should navigate to page 2'() {
        given: 'I create a new report'
        to ShortFormatPreSentenceReportPageFrame
        and: 'I return to document list'
        to DocumentListPage
        and: 'Update that new report'
        firstDocumentUpdateLink.click(ShortFormatPreSentenceReportUpdatePageFrame)

        when: 'Continue to edit the report'
        withFrame(newTechFrame, SFRPSWelcomePage) {
            continueNowButton.click()
        }

        then: 'I am taken to the second page in the report'
        withWindow("reportpopup") {
            at(SFRPSOffenderDetailsPage)
        }
    }
}
