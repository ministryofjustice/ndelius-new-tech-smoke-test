package uk.gov.justice.digital.hmpps.delius.pages

import geb.Page

class ShortFormatPreSentenceReportUpdatePageFrame extends Page {
    static url = '/sfpsr_update'

    static at = {
        waitFor(5) {
            browser.currentUrl.contains(url)
        }
    }

    static content = {
        newTechFrame {$('iframe')}
    }
}
