package uk.gov.justice.digital.hmpps.delius.pages

import geb.Page

class SFRPSDraftSavedPage extends Page {

    static at = { heading == "Draft report saved" }

    static content = {
        heading { $("h1").text() }
        documentListLink {$("#document-list a") }
    }
}
