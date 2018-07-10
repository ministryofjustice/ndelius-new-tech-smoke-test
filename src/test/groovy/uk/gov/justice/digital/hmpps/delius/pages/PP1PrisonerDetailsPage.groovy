package uk.gov.justice.digital.hmpps.delius.pages

class PP1PrisonerDetailsPage extends SFPSRBasePage {

    static at = { heading == "Prisoner details" }

    static content = {
        heading { $("h1").text() }
    }
}
