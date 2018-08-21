package uk.gov.justice.digital.hmpps.delius.pages

class PP1InterventionsPage extends PP1BasePage {

    static at = { heading == "Interventions" }

    static content = {
        fillInterventionsDetailWith { text -> $("#interventionsDetail .ql-editor").leftShift(text) }
        fillInterventionsSummaryWith { text -> $("#interventionsSummary .ql-editor").leftShift(text) }
    }
}
