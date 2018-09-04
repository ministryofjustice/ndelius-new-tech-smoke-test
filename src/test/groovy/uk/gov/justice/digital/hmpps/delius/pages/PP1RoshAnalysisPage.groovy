package uk.gov.justice.digital.hmpps.delius.pages

class PP1RoshAnalysisPage extends PP1BasePage {
    static at = { heading == "RoSH analysis" }

    static content = {
        fillNatureOfRisk { text -> $("#natureOfRisk .ql-editor").leftShift(text) }
        fillIncreaseFactors { text -> $("#increaseFactors .ql-editor").leftShift(text) }
        fillDecreaseFactors { text -> $("#decreaseFactors .ql-editor").leftShift(text) }
        fillLikelihoodFurtherOffending { text -> $("#likelihoodFurtherOffending .ql-editor").leftShift(text) }
        setRiskOfAbscondingYes { $("#riskOfAbsconding_yes").value("yes") }
        fillRiskOfAbscondingDetails { text -> $("#riskOfAbscondingDetails .ql-editor").leftShift(text) }
    }
}
