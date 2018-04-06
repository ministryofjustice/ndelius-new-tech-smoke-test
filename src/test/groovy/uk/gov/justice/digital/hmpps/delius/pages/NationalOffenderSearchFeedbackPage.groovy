package uk.gov.justice.digital.hmpps.delius.pages

import geb.Page

class NationalOffenderSearchFeedbackPage extends Page {
    static at = { heading ==  "Give feedback" }

    static content = {
        heading { $("h1").text() }
        selectRole {role -> $("#role").value(role)}
        selectProvider {provider -> $("#provider").value(provider)}
        selectRegion {region -> $("#region").value(region)}
        selectRating {rating -> $("input", value: rating).click()}
        fillFeedbackWith {feedback -> $("#feedback").value(feedback)}
        submitFeedbackButton {$("input", type: "submit")}
    }
}
