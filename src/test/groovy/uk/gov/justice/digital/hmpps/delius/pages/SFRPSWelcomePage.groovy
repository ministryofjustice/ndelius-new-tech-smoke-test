package uk.gov.justice.digital.hmpps.delius.pages

import geb.Page

class SFRPSWelcomePage extends Page {

    static at = { heading == "Short Format Pre-sentence Report" }

    static content = {
        heading { $("h1").text() }
        startNowButton { $("button", text: "Start now") }
        continueNowButton { $("button", text: "Continue now") }
        feedbackLink {$("a", text: "feedback")}
        documentListLink {$("a", text: "Back to document list")}
    }
}
