package uk.gov.justice.digital.hmpps.delius.pages

class PP1RoshAnalysisPage extends PP1BasePage {
    static at = { heading == "RoSH analysis" }

    static content = {
        fillNatureOfRisk { text -> js.exec("return tinymce.get('natureOfRisk').setContent('$text')") }
        fillIncreaseFactors { text -> js.exec("return tinymce.get('increaseFactors').setContent('$text')") }
        fillDecreaseFactors { text -> js.exec("return tinymce.get('decreaseFactors').setContent('$text')") }
        fillLikelihoodFurtherOffending { text -> js.exec("return tinymce.get('likelihoodFurtherOffending').setContent('$text')") }
        setRiskOfAbscondingYes { $("#riskOfAbsconding_yes").value("yes") }
        fillRiskOfAbscondingDetails { text -> js.exec("return tinymce.get('riskOfAbscondingDetails').setContent('$text')") }
    }
}
