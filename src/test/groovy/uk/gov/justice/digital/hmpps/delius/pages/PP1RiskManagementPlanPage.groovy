package uk.gov.justice.digital.hmpps.delius.pages

class PP1RiskManagementPlanPage extends PP1BasePage {
    static at = { heading == "Risk Management Plan (RMP)" }

    static content = {
        setRiskManagementPlanRequiredYes { $("#riskManagementPlanRequired_yes").value("yes") }
        fillCurrentSituation { text -> $("#currentSituation .ql-editor").leftShift(text) }
        fillSupportingAgencies { text -> $("#supportingAgencies .ql-editor").leftShift(text) }
        fillControl { text -> $("#control .ql-editor").leftShift(text) }
        fillRiskMeasures { text -> $("#riskMeasures .ql-editor").leftShift(text) }
        fillAgencyActions { text -> $("#agencyActions .ql-editor").leftShift(text) }
        fillAdditionalConditions { text -> $("#additionalConditions .ql-editor").leftShift(text) }
        fillLevelOfContact { text -> $("#levelOfContact .ql-editor").leftShift(text) }
        fillContingencyPlan { text -> $("#contingencyPlan .ql-editor").leftShift(text) }
    }
}
