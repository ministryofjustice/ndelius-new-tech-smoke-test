package uk.gov.justice.digital.hmpps.delius.pages

class SFRPSOffenceDetailsPage extends SFPSRBasePage {

    static at = { heading == "Offence details" }

    static content = {
        heading { $("h1").text() }
        fillOffenceSummaryWith { text -> $("#offenceSummary .ql-editor").leftShift(text) }
    }
}
