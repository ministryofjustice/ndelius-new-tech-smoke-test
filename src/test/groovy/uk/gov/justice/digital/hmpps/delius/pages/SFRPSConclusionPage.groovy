package uk.gov.justice.digital.hmpps.delius.pages

class SFRPSConclusionPage extends SFPSRBasePage {

    static at = { heading ==  "Proposal" }

    static content = {
        heading { $("h1").text() }
        fillProposalWith { text -> js.exec("return tinymce.get('proposal').setContent('$text')") }
    }
}
