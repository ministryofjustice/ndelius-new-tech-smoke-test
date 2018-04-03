package uk.gov.justice.digital.hmpps.delius.pages

class SFRPSFeedbackPage extends SFPSRBasePage {

    static content = {
        headingDisplayed { waitFor(5) { $("h1", text: "Feedback") } }
        fillFeedbackWith { text -> $("#feedback").value(text) }
        submitYourFeedbackButton { $("input[value='Submit your feedback']") }
    }
}
