package uk.gov.justice.digital.hmpps.delius.pages

import geb.Page

class PP1DocumentListPage extends Page {
    static url = '/paroleParom1Report_list'

    static at = {
        waitFor(5) {
            browser.currentUrl.endsWith(url) || browser.currentUrl.endsWith(url + '#')
        }
    }

    static content = {
        documentRows {$('table tbody tr')}
        firstDocumentUpdateLink {$("a", text: "Update")}
        firstDocumentViewLink {$("a", text: "View")}
    }
}
