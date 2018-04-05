package uk.gov.justice.digital.hmpps.delius.pages

class SFRPSOffenderDetailsPage extends SFPSRBasePage {

    static at = { heading == "Offender details" }

    static content = {
        heading { $("h1").text() }
        address { $("#address") }
        fillAddressWith { text -> $("#address").value(text) }
        fillPncWith { text -> $("#pnc").value(text) }
    }
}
