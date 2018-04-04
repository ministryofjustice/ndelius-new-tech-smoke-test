package uk.gov.justice.digital.hmpps.delius.pages

class SFRPSOffenceDetailsPage extends SFPSRBasePage {

    static at = { heading == "Offence details" }

    static content = {
        heading { $("h1").text() }
        fillMainOffenceWith { text -> $("#mainOffence").value(text) }
        fillOtherOffencesWith { text -> $("#otherOffences").value(text) }
        fillOffenceSummaryWith { text -> $("#offenceSummary").value(text) }
    }
}
