package uk.gov.justice.digital.hmpps.delius.pages

class PP1ResettlementPlanPage extends PP1BasePage {

    static at = { heading == "Resettlement plan for release" }

    static content = {
        setResettlementplanYes { $("#resettlementPlan_yes").value("yes") }
        fillResettlementPlanDetail { text -> js.exec("return tinymce.get('resettlementPlanDetail').setContent('$text')") }
    }

}