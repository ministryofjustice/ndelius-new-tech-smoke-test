package uk.gov.justice.digital.hmpps.delius.pages

class SFRPSOffenceAnalysisPage extends SFPSRBasePage {

    static at = { heading == "Offence analysis" }

    static content = {
        heading { $("h1").text() }
        fillOffenceAnalysisWith { text -> $("#offenceAnalysis  .ql-editor").leftShift(text) }
        fillPatternOfOffendingWith { text -> $("#patternOfOffending  .ql-editor").leftShift(text) }
    }
}
