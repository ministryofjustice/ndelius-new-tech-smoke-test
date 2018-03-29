package uk.gov.justice.digital.hmpps.delius.pages

class SFRPSOffenderDetailsPage extends SFPSRBasePage {

    static content = {
        headingDisplayed { waitFor(5) { $("h1", text: "Offender details") } }
        address { $("#address") }
        fillAddressWith { text -> $("#address").value(text) }
        fillPncWith { text -> $("#pnc").value(text) }
    }
}
