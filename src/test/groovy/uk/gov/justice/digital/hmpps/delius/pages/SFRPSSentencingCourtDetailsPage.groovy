package uk.gov.justice.digital.hmpps.delius.pages

class SFRPSSentencingCourtDetailsPage extends SFPSRBasePage {

    static content = {
        headingDisplayed { waitFor(5) { $("h1", text: "Sentencing court details") } }
        fillCourtWith { text -> $("#court").value(text) }
        fillLocalJusticeAreaWith { text -> $("#localJusticeArea").value(text) }
    }
}
