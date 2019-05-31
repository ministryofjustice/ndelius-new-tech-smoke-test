package uk.gov.justice.digital.hmpps.delius.pages

class SFRPSConclusionPage extends SFPSRBasePage {

    static at = { heading ==  "Proposal" }

    static content = {
        heading { $("h1").text() }
        fillProposalWith { text -> js.exec("tinymce.get('proposal-tinymce').setContent('$text'); tinymce.get('proposal-tinymce').fire('blur'); return true") }
    }
}
