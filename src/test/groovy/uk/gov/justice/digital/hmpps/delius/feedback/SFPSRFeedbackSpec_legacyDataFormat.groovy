package uk.gov.justice.digital.hmpps.delius.feedback

import geb.spock.GebReportingSpec
import spock.lang.Stepwise
import uk.gov.justice.digital.hmpps.delius.dataload.FeedbackDataLoader
import uk.gov.justice.digital.hmpps.delius.pages.*

@Stepwise
class SFPSRFeedbackSpec_legacyDataFormat extends GebReportingSpec {

    def setupSpec() {
        FeedbackDataLoader.clearSfpsrFeedback()
        FeedbackDataLoader.seedLegacySfpsrFeedbackDataFormat()
        to IndexPage
    }

    def 'Legacy feedback submitted is viewable on protected page'() {

        when: 'I view the feedback viewer page'
        to SFPSRFeedbackViewerPage

        then: 'I see the legacy feedback previously submitted'
        feedbackRows.size() == 1
        feedbackRows.text().contains('')
        feedbackRows.text().contains('')
        feedbackRows.text().contains('')
        feedbackRows.text().contains('')
        feedbackRows.text().contains('')
        feedbackRows.text().contains('A legacy feedback comment')
    }
}
