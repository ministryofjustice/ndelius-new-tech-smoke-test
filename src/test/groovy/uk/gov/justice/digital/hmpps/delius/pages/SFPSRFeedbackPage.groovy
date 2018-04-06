package uk.gov.justice.digital.hmpps.delius.pages

import geb.Page

class SFPSRFeedbackPage extends Page {
    static at = { heading ==  "Feedback" }

    static content = {
        heading { $("h1").text() }
        fillFeedbackWith {feedback -> $("#feedback").value(feedback)}
        submitFeedbackButton {$("input", type: "submit")}
    }
}
