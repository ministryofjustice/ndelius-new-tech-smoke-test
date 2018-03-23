package uk.gov.justice.digital.hmpps.delius.shortformtpresentencereport

import geb.spock.GebReportingSpec
import spock.lang.Stepwise
import uk.gov.justice.digital.hmpps.delius.dataload.SFPSRDataLoader
import uk.gov.justice.digital.hmpps.delius.pages.DocumentListPage
import uk.gov.justice.digital.hmpps.delius.pages.IndexPage
import uk.gov.justice.digital.hmpps.delius.pages.SFRPSOffenderDetailsPage
import uk.gov.justice.digital.hmpps.delius.pages.SFRPSWelcomePage
import uk.gov.justice.digital.hmpps.delius.pages.ShortFormatPreSentenceReportPageFrame

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
        withFrame(newTechFrame, SFRPSOffenderDetailsPage) {
            waitFor {saveDraftLink.isDisplayed()}
            saveDraftLink.click()
        }

        then: 'I return to the document list'
        at DocumentListPage

    }
}
