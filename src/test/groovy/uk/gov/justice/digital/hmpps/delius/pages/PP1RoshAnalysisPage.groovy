package uk.gov.justice.digital.hmpps.delius.pages

class PP1RoshAnalysisPage extends PP1BasePage {
    static at = { heading == "RoSH analysis" }

    static content = {
        fillNatureOfRisk { text -> $("#natureOfRisk").value(text) }
        fillIncreaseFactors { text -> $("#increaseFactors").value(text) }
        fillDecreaseFactors { text -> $("#decreaseFactors").value(text) }
        fillLikelihoodFurtherOffending { text -> $("#likelihoodFurtherOffending").value(text) }
        setRiskOfAbscondingYes { $("#riskOfAbsconding_yes").value("yes") }
        fillRiskOfAbscondingDetails { text -> $("#riskOfAbscondingDetails").value(text) }
    }
}
