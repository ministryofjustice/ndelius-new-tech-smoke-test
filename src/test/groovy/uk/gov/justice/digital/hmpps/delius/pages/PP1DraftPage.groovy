package uk.gov.justice.digital.hmpps.delius.pages

import geb.Page

class PP1DraftPage extends Page {

    static at = { heading == "Draft report saved" }

    static content = {
        heading { $("h1").text() }
        closeLink {$("#close-report a") }
    }
}
