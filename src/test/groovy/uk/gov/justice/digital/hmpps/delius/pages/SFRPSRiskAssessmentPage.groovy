package uk.gov.justice.digital.hmpps.delius.pages

class SFRPSRiskAssessmentPage extends SFPSRBasePage {

    static at = { heading ==  "Risk assessment" }

    static content = {
        heading { $("h1").text() }
        fillLikelihoodOfReOffendingWith { text -> $("#likelihoodOfReOffending .ql-editor").leftShift(text) }
        fillRiskOfSeriousHarmWith { text -> $("#riskOfSeriousHarm .ql-editor").leftShift(text) }
        setPreviousSupervisionResponseGood { $( "#previousSupervisionResponse_good").value("good") }
        fillAdditionalPreviousSupervisionWith { text -> $("#additionalPreviousSupervision .ql-editor").leftShift(text) }
    }
}
