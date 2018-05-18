package uk.gov.justice.digital.hmpps.delius.shortformtpresentencereport

import geb.spock.GebReportingSpec
import spock.lang.Stepwise
import uk.gov.justice.digital.hmpps.delius.dataload.SFPSRDataLoader
import uk.gov.justice.digital.hmpps.delius.pages.DocumentListPage
import uk.gov.justice.digital.hmpps.delius.pages.IndexPage
import uk.gov.justice.digital.hmpps.delius.pages.SFRPSDraftSavedPage
import uk.gov.justice.digital.hmpps.delius.pages.SFRPSOffenderDetailsPage
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
        withWindow("report-popup") {
            at(SFRPSOffenderDetailsPage)
            saveDraftLink.click()
        }
        and: 'I click close window'
        withWindow("report-popup") {
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
        withWindow("report-popup") {
            at(SFRPSOffenderDetailsPage)
            saveDraftLink.isDisplayed()
        }

    }
}
