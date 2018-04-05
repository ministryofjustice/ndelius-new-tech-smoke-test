package uk.gov.justice.digital.hmpps.delius.pages

class SFRPSConclusionPage extends SFPSRBasePage {

    static at = { heading ==  "Conclusion" }

    static content = {
        heading { $("h1").text() }
        fillProposalWith { text -> $("#proposal").value(text) }
    }
}
