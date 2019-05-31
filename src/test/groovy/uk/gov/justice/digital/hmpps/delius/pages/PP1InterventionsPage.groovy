package uk.gov.justice.digital.hmpps.delius.pages

class PP1InterventionsPage extends PP1BasePage {

    static at = { heading == "Interventions" }

    static content = {
        fillInterventionsDetailWith { text -> js.exec("tinymce.get('interventionsDetail-tinymce').setContent('$text'); tinymce.get('interventionsDetail-tinymce').fire('blur'); return true") }
        fillInterventionsSummaryWith { text -> js.exec("tinymce.get('interventionsSummary-tinymce').setContent('$text'); tinymce.get('interventionsSummary-tinymce').fire('blur'); return true") }
    }
}
