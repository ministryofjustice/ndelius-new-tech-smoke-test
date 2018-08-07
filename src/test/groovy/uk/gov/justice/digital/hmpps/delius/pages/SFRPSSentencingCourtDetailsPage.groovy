package uk.gov.justice.digital.hmpps.delius.pages

class SFRPSSentencingCourtDetailsPage extends SFPSRBasePage {

    static at = { heading == "Sentencing court details" }

    static content = {
        heading { $("h1").text() }
        fillLocalJusticeAreaWith { text -> $("#localJusticeArea").value(text) }
    }
}
