package uk.gov.justice.digital.hmpps.delius.pages

class PP1InterventionsPage extends PP1BasePage {

    static at = { heading == "Interventions" }

    static content = {
        fillInterventionsDetailWith { text -> $("#interventionsDetail").value(text) }
        fillInterventionsSummaryWith { text -> $("#interventionsSummary").value(text) }
    }
}
