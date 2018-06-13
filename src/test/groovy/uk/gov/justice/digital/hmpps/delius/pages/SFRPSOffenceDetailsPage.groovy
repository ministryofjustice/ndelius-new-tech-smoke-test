package uk.gov.justice.digital.hmpps.delius.pages

class SFRPSOffenceDetailsPage extends SFPSRBasePage {

    static at = { heading == "Offence details" }

    static content = {
        heading { $("h1").text() }
        fillMainOffenceWith { text -> $("#mainOffence .ql-editor").leftShift(text) }
        fillOtherOffencesWith { text -> $("#otherOffences .ql-editor").leftShift(text) }
        fillOffenceSummaryWith { text -> $("#offenceSummary .ql-editor").leftShift(text) }
    }
}
