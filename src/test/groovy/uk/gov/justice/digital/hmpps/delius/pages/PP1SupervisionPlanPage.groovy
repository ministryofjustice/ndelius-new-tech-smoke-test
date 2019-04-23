package uk.gov.justice.digital.hmpps.delius.pages

class PP1SupervisionPlanPage extends PP1BasePage {

    static at = { heading == "Supervision plan for release" }

    static content = {
        setSupervisionPlanRequiredYes { $("#supervisionPlanRequired_yes").value("yes") }
        fillSupervisionPlanDetailWith { text -> $("#supervisionPlanDetail").value(text) }
    }


}