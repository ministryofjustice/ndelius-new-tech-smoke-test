package uk.gov.justice.digital.hmpps.delius.pages

class SFRPSRiskAssessmentPage extends SFPSRBasePage {

    static at = { heading ==  "Risk assessment" }

    static content = {
        heading { $("h1").text() }
        fillLikelihoodOfReOffendingWith { text -> $("#likelihoodOfReOffending").value(text) }
        fillRiskOfSeriousHarmWith { text -> $("#riskOfSeriousHarm").value(text) }
        setPreviousSupervisionResponseGood { $( "#previousSupervisionResponse_Good").value("Good") }
        fillAdditionalPreviousSupervisionWith { text -> $("#additionalPreviousSupervision").value(text) }
    }
}
