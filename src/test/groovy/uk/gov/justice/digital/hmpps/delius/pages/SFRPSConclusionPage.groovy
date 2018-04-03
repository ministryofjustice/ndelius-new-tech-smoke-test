package uk.gov.justice.digital.hmpps.delius.pages

class SFRPSConclusionPage extends SFPSRBasePage {

    static content = {
        headingDisplayed { waitFor(5) { $("h1", text: "Conclusion") } }
        fillProposalWith { text -> $("#proposal").value(text) }
    }
}
