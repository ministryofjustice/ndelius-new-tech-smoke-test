package uk.gov.justice.digital.hmpps.delius.pages

class PP1RoshAnalysisPage extends PP1BasePage {
    static at = { heading == "RoSH analysis" }

    static content = {
        fillNatureOfRisk { text -> js.exec("tinymce.get('natureOfRisk-tinymce').setContent('$text'); tinymce.get('natureOfRisk-tinymce').fire('blur'); return true") }
        fillIncreaseFactors { text -> js.exec("tinymce.get('increaseFactors-tinymce').setContent('$text'); tinymce.get('increaseFactors-tinymce').fire('blur'); return true") }
        fillDecreaseFactors { text -> js.exec("tinymce.get('decreaseFactors-tinymce').setContent('$text'); tinymce.get('decreaseFactors-tinymce').fire('blur'); return true") }
        fillLikelihoodFurtherOffending { text -> js.exec("tinymce.get('likelihoodFurtherOffending-tinymce').setContent('$text'); tinymce.get('likelihoodFurtherOffending-tinymce').fire('blur'); return true") }
        setRiskOfAbscondingYes { $("#riskOfAbsconding_yes").value("yes") }
        fillRiskOfAbscondingDetails { text -> js.exec("tinymce.get('riskOfAbscondingDetails-tinymce').setContent('$text'); tinymce.get('riskOfAbscondingDetails-tinymce').fire('blur'); return true") }
    }
}
