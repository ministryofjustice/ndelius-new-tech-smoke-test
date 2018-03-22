package uk.gov.justice.digital.hmpps.delius.pages

import geb.Page

class ShortFormatPreSentenceReportPageFrame extends Page {
    static url = '/sfpsr'

    static at = {
        waitFor(5) {
            browser.currentUrl.endsWith(url) || browser.currentUrl.endsWith(url + '#')
        }
    }

    static content = {
        newTechFrame {$('iframe')}
    }
}
