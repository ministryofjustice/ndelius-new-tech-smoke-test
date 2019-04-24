package uk.gov.justice.digital.hmpps.delius.pages

class PP1InterventionsPage extends PP1BasePage {

    static at = { heading == "Interventions" }

    static content = {
        fillInterventionsDetailWith { text -> js.exec("return tinymce.get('interventionsDetail').setContent('$text')") }
        fillInterventionsSummaryWith { text -> js.exec("return tinymce.get('interventionsSummary').setContent('$text')") }
    }
}
