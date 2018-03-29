package uk.gov.justice.digital.hmpps.delius.pages

class SFRPSOffenceDetailsPage extends SFPSRBasePage {

    static content = {
        headingDisplayed { waitFor(5) { $("h1", text: "Offence details") } }
        fillMainOffenceWith { text -> $("#mainOffence").value(text) }
        fillOtherOffencesWith { text -> $("#otherOffences").value(text) }
        fillOffenceSummaryWith { text -> $("#offenceSummary").value(text) }
    }
}
