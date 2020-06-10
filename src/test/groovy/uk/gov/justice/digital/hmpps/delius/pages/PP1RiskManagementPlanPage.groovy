package uk.gov.justice.digital.hmpps.delius.pages

class PP1RiskManagementPlanPage extends PP1BasePage {
    static at = { heading == "Risk Management Plan (RMP)" }

    static content = {
        setRiskManagementPlanRequiredYes { $("#riskManagementPlanRequired_yes").value("yes") }
        fillCurrentSituation { text -> js.exec("tinymce.get('currentSituation-tinymce').setContent('$text'); tinymce.get('currentSituation-tinymce').fire('blur'); return true") }
        fillSupervision { text -> js.exec("tinymce.get('supervision-tinymce').setContent('$text'); tinymce.get('supervision-tinymce').fire('blur'); return true") }
        fillMonitoringControl { text -> js.exec("tinymce.get('monitoringControl-tinymce').setContent('$text'); tinymce.get('monitoringControl-tinymce').fire('blur'); return true") }
        fillInterventionsTreatment { text -> js.exec("tinymce.get('interventionsTreatment-tinymce').setContent('$text'); tinymce.get('interventionsTreatment-tinymce').fire('blur'); return true") }
        fillVictimSafetyPlanning { text -> js.exec("tinymce.get('victimSafetyPlanning-tinymce').setContent('$text'); tinymce.get('victimSafetyPlanning-tinymce').fire('blur'); return true") }
        fillContingencyPlan { text -> js.exec("tinymce.get('contingencyPlan-tinymce').setContent('$text'); tinymce.get('contingencyPlan-tinymce').fire('blur'); return true") }
    }
}
