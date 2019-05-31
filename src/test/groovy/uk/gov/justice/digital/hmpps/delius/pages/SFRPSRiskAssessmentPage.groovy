package uk.gov.justice.digital.hmpps.delius.pages

class SFRPSRiskAssessmentPage extends SFPSRBasePage {

    static at = { heading ==  "Risk assessment" }

    static content = {
        heading { $("h1").text() }
        fillLikelihoodOfReOffendingWith { text -> js.exec("tinymce.get('likelihoodOfReOffending-tinymce').setContent('$text'); tinymce.get('likelihoodOfReOffending-tinymce').fire('blur'); return true") }
        fillRiskOfSeriousHarmWith { text -> js.exec("tinymce.get('riskOfSeriousHarm-tinymce').setContent('$text'); tinymce.get('riskOfSeriousHarm-tinymce').fire('blur'); return true") }
        setPreviousSupervisionResponseGood { $( "#previousSupervisionResponse_Good").value("Good") }
        fillAdditionalPreviousSupervisionWith { text -> js.exec("tinymce.get('additionalPreviousSupervision-tinymce').setContent('$text'); tinymce.get('additionalPreviousSupervision-tinymce').fire('blur'); return true") }
    }
}
