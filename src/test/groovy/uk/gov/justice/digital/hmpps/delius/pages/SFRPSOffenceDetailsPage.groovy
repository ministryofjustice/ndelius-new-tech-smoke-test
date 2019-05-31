package uk.gov.justice.digital.hmpps.delius.pages

class SFRPSOffenceDetailsPage extends SFPSRBasePage {

    static at = { heading == "Offence details" }

    static content = {
        heading { $("h1").text() }
        fillMainOffenceWith { text -> js.exec("tinymce.get('mainOffence-tinymce').setContent('$text'); tinymce.get('mainOffence-tinymce').fire('blur'); return true") }
        fillOtherOffencesWith { text -> js.exec("tinymce.get('otherOffences-tinymce').setContent('$text'); tinymce.get('otherOffences-tinymce').fire('blur'); return true") }
        fillOffenceSummaryWith { text -> js.exec("tinymce.get('offenceSummary-tinymce').setContent('$text'); tinymce.get('offenceSummary-tinymce').fire('blur'); return true") }
    }
}
