package uk.gov.justice.digital.hmpps.delius.pages

class SFRPSRiskAssessmentPage extends SFPSRBasePage {

    static content = {
        headingDisplayed { waitFor(5) { $("h1", text: "Risk assessment") } }
        fillLikelihoodOfReOffendingWith { text -> $("#likelihoodOfReOffending").value(text) }
        fillRiskOfSeriousHarmWith { text -> $("#riskOfSeriousHarm").value(text) }
        setPreviousSupervisionResponseGood { $( "#previousSupervisionResponse_Good").value("Good") }
        fillAdditionalPreviousSupervisionWith { text -> $("#additionalPreviousSupervision").value(text) }
    }
}
