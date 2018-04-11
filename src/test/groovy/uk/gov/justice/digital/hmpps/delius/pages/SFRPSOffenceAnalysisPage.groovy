package uk.gov.justice.digital.hmpps.delius.pages

class SFRPSOffenceAnalysisPage extends SFPSRBasePage {

    static at = { heading == "Offence analysis" }

    static content = {
        heading { $("h1").text() }
        fillOffenceAnalysisWith { text -> $("#offenceAnalysis").value(text) }
        fillPatternOfOffendingWith { text -> $("#patternOfOffending").value(text) }
    }
}
