package uk.gov.justice.digital.hmpps.delius.pages

import geb.Page

class ParoleParom1ReportPageFrame extends Page {
    static url = '/paroleParom1Report'

    static at = {
        waitFor(5) {
            browser.currentUrl.endsWith(url) || browser.currentUrl.endsWith(url + '#')
        }
    }

    static content = {
        newTechFrame {$('iframe')}
    }
}
