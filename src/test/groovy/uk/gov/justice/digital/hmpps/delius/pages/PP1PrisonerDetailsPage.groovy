package uk.gov.justice.digital.hmpps.delius.pages

class PP1PrisonerDetailsPage extends SFPSRBasePage {

    static at = { $("h1").text() == "Prisoner details" }

    static content = {
        heading { $("h1").text() }
    }
}
