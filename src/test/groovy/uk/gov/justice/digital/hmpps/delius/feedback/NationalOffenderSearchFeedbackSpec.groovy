package uk.gov.justice.digital.hmpps.delius.feedback

import geb.spock.GebReportingSpec
import spock.lang.Stepwise
import uk.gov.justice.digital.hmpps.delius.dataload.FeedbackDataLoader
import uk.gov.justice.digital.hmpps.delius.pages.IndexPage

import uk.gov.justice.digital.hmpps.delius.pages.NationalOffenderSearchFeedbackPage
import uk.gov.justice.digital.hmpps.delius.pages.NationalOffenderSearchFeedbackViewerPage
import uk.gov.justice.digital.hmpps.delius.pages.NationalOffenderSearchPage
import uk.gov.justice.digital.hmpps.delius.pages.NationalOffenderSearchPageFrame


@Stepwise
class NationalOffenderSearchFeedbackSpec extends GebReportingSpec {

    def setupSpec() {
        FeedbackDataLoader.clearNationalSearchFeedback()
        to IndexPage
    }

    def 'Feedback submitted is viewable on protected page'() {

        given: 'I submit feedback about the search service'
        to NationalOffenderSearchPageFrame
        withFrame(newTechFrame, NationalOffenderSearchPage) {
            feedbackLink.click(NationalOffenderSearchFeedbackPage)
            fillEmailWith("foo@bar.com")
            selectRole('Case Administrator')
            selectProvider('CRC')
            selectRegion('London')
            selectRating('Satisfied')
            fillFeedbackWith('Fantastic service!')
            submitFeedbackButton.click()
        }

        when: 'I view the feedback viewer page'
        to NationalOffenderSearchFeedbackViewerPage

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
