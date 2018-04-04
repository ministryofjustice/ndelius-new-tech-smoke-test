package uk.gov.justice.digital.hmpps.delius.pages

class SFRPSFeedbackPage extends SFPSRBasePage {

    static at = { heading == "Feedback" }

    static content = {
        heading { $("h1").text() }
        fillFeedbackWith { text -> $("#feedback").value(text) }
        submitYourFeedbackButton { $("input[value='Submit your feedback']") }
    }
}
