package uk.gov.justice.digital.hmpps.delius.pages

import geb.Page

class PP1LandingPage extends Page {

    static at = { heading == "Start a PAROM 1" }

    static content = {
        heading { $("h1").text() }
        startNowButton { $("button", text: "Start now") }
    }
}
