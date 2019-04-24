package uk.gov.justice.digital.hmpps.delius.pages

class PP1RiskManagementPlanPage extends PP1BasePage {
    static at = { heading == "Risk Management Plan (RMP)" }

    static content = {
        setRiskManagementPlanRequiredYes { $("#riskManagementPlanRequired_yes").value("yes") }
        fillCurrentSituation { text -> js.exec("return tinymce.get('currentSituation').setContent('$text')") }
        fillSupportingAgencies { text -> js.exec("return tinymce.get('supportingAgencies').setContent('$text')") }
        fillSupport { text -> js.exec("return tinymce.get('support').setContent('$text')") }
        fillControl { text -> js.exec("return tinymce.get('control').setContent('$text')") }
        fillRiskMeasures { text -> js.exec("return tinymce.get('riskMeasures').setContent('$text')") }
        fillAgencyActions { text -> js.exec("return tinymce.get('agencyActions').setContent('$text')") }
        fillAdditionalConditions { text -> js.exec("return tinymce.get('additionalConditions').setContent('$text')") }
        fillLevelOfContact { text -> js.exec("return tinymce.get('levelOfContact').setContent('$text')") }
        fillContingencyPlan { text -> js.exec("return tinymce.get('contingencyPlan').setContent('$text')") }
    }
}
