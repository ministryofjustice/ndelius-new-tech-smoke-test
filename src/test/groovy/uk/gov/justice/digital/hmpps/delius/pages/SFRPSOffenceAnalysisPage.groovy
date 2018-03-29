package uk.gov.justice.digital.hmpps.delius.pages

class SFRPSOffenceAnalysisPage extends SFPSRBasePage {

    static content = {
        headingDisplayed { waitFor(5) { $("h1", text: "Offence analysis") } }
        fillOffenceAnalysisWith { text -> $("#offenceAnalysis").value(text) }
        fillPatternOfOffendingWith { text -> $("#patternOfOffending").value(text) }
    }
}
