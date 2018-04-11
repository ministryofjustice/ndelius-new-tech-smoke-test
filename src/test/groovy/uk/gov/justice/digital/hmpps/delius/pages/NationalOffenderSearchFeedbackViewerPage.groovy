package uk.gov.justice.digital.hmpps.delius.pages

import geb.Page

import static uk.gov.justice.digital.hmpps.delius.Config.newTechFeedbackBaseUrl

class NationalOffenderSearchFeedbackViewerPage extends Page {
    static url = buildUrl()

    static at = {
        waitFor() {
            browser.currentUrl.contains('feedback')
        }
    }


    static content = {
        feedbackRows {$('table tbody tr')}
    }

    static buildUrl() {
        // special case for feedback viewer - since this has basic auth and Selenium
        // has inconsistent support for basic auth we navigate straight to page authenticating
        // using the username:password url format
        return newTechFeedbackBaseUrl() + '/nationalSearch'
    }
}
