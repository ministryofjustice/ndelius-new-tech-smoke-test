package uk.gov.justice.digital.hmpps.delius.pages

import geb.Page

class NationalOffenderSearchFeedbackViewerPage extends Page {
    // special case for feedback viewer - since this has basic auth and Selenium
    // has inconsistent support for basic auth we navigate straight to page authenticating
    // using the username:password url format
    static url = System.getenv('NEW_TECH_FEEDBACK_URL') ?: 'http://feedback.user:changeit@localhost:9000/feedback'

    static at = {
        waitFor() {
            browser.currentUrl.contains('feedback')
        }
    }


    static content = {
        feedbackRows {$('table tbody tr')}
    }
}
