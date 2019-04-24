package uk.gov.justice.digital.hmpps.delius.pages

class SFRPSRiskAssessmentPage extends SFPSRBasePage {

    static at = { heading ==  "Risk assessment" }

    static content = {
        heading { $("h1").text() }
        fillLikelihoodOfReOffendingWith { text -> js.exec("return tinymce.get('likelihoodOfReOffending').setContent('$text')") }
        fillRiskOfSeriousHarmWith { text -> js.exec("return tinymce.get('riskOfSeriousHarm').setContent('$text')") }
        setPreviousSupervisionResponseGood { $( "#previousSupervisionResponse_Good").value("Good") }
        fillAdditionalPreviousSupervisionWith { text -> js.exec("return tinymce.get('additionalPreviousSupervision').setContent('$text')") }
    }
}
