package uk.gov.justice.digital.hmpps.delius.feedback

import geb.spock.GebReportingSpec
import spock.lang.Stepwise
import uk.gov.justice.digital.hmpps.delius.dataload.FeedbackDataLoader
import uk.gov.justice.digital.hmpps.delius.pages.*

@Stepwise
class SFPSRFeedbackSpec extends GebReportingSpec {

    def setupSpec() {
        FeedbackDataLoader.clearSfpsrFeedback()
        FeedbackDataLoader.seedLotsOfSfpsrAnalyticsData()
        to IndexPage
    }

    def 'Feedback submitted is viewable on protected page'() {

        given: 'I submit feedback about the search service'
        to ShortFormatPreSentenceReportPageFrame
        withFrame(newTechFrame, SFRPSWelcomePage) {
            feedbackLink.click(SFPSRFeedbackPage)
            fillEmailWith("foo@bar.com")
            selectRole('Case Administrator')
            selectProvider('CRC')
            selectRegion('London')
            selectRating('Satisfied')
            fillFeedbackWith('Fantastic service!')
            submitFeedbackButton.click()
        }

        when: 'I view the feedback viewer page'
        to SFPSRFeedbackViewerPage

        then: 'I see the feedback previously submitted'
        feedbackRows.size() == 1
        feedbackRows.text().contains('foo@bar.com')
        feedbackRows.text().contains('Case Administrator')
        feedbackRows.text().contains('CRC')
        feedbackRows.text().contains('London')
        feedbackRows.text().contains('Satisfied')
        feedbackRows.text().contains('Fantastic service!')
    }

}
