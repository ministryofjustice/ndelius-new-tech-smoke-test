package uk.gov.justice.digital.hmpps.delius.pages

class PP1RiskManagementPlanPage extends PP1BasePage {
    static at = { heading == "Risk Management Plan (RMP)" }

    static content = {
        setRiskManagementPlanRequiredYes { $("#riskManagementPlanRequired_yes").value("yes") }
        fillCurrentSituation { text -> $("#currentSituation").value(text) }
        fillSupportingAgencies { text -> $("#supportingAgencies").value(text) }
        fillSupport { text -> $("#support").value(text) }
        fillControl { text -> $("#control").value(text) }
        fillRiskMeasures { text -> $("#riskMeasures").value(text) }
        fillAgencyActions { text -> $("#agencyActions").value(text) }
        fillAdditionalConditions { text -> $("#additionalConditions").value(text) }
        fillLevelOfContact { text -> $("#levelOfContact").value(text) }
        fillContingencyPlan { text -> $("#contingencyPlan").value(text) }
    }
}
