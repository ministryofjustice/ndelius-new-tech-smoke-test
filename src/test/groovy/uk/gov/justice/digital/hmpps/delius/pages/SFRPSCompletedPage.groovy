package uk.gov.justice.digital.hmpps.delius.pages

import geb.Page

class SFRPSCompletedPage extends Page {

    static at = { heading == "Report saved" }

    static content = {
        heading { $("h1").text() }
        closeLink {$("#close-report a") }
    }
}
