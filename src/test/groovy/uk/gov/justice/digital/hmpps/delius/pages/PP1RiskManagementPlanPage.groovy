package uk.gov.justice.digital.hmpps.delius.pages

class PP1RiskManagementPlanPage extends PP1BasePage {
    static at = { heading == "Risk Management Plan (RMP)" }

    static content = {
        setRiskManagementPlanRequiredYes { $("#riskManagementPlanRequired_yes").value("yes") }
        fillCurrentSituation { text -> js.exec("tinymce.get('currentSituation-tinymce').setContent('$text'); tinymce.get('currentSituation-tinymce').fire('blur'); return true") }
        fillSupportingAgencies { text -> js.exec("tinymce.get('supportingAgencies-tinymce').setContent('$text'); tinymce.get('supportingAgencies-tinymce').fire('blur'); return true") }
        fillSupport { text -> js.exec("tinymce.get('support-tinymce').setContent('$text'); tinymce.get('support-tinymce').fire('blur'); return true") }
        fillControl { text -> js.exec("tinymce.get('control-tinymce').setContent('$text'); tinymce.get('control-tinymce').fire('blur'); return true") }
        fillRiskMeasures { text -> js.exec("tinymce.get('riskMeasures-tinymce').setContent('$text'); tinymce.get('riskMeasures-tinymce').fire('blur'); return true") }
        fillAgencyActions { text -> js.exec("tinymce.get('agencyActions-tinymce').setContent('$text'); tinymce.get('agencyActions-tinymce').fire('blur'); return true") }
        fillAdditionalConditions { text -> js.exec("tinymce.get('additionalConditions-tinymce').setContent('$text'); tinymce.get('additionalConditions-tinymce').fire('blur'); return true") }
        fillLevelOfContact { text -> js.exec("tinymce.get('levelOfContact-tinymce').setContent('$text'); tinymce.get('levelOfContact-tinymce').fire('blur'); return true") }
        fillContingencyPlan { text -> js.exec("tinymce.get('contingencyPlan-tinymce').setContent('$text'); tinymce.get('contingencyPlan-tinymce').fire('blur'); return true") }
    }
}
