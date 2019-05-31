package uk.gov.justice.digital.hmpps.delius.pages

class SFRPSOffenceAnalysisPage extends SFPSRBasePage {

    static at = { heading == "Offence analysis" }

    static content = {
        heading { $("h1").text() }
        fillOffenceAnalysisWith { text -> js.exec("tinymce.get('offenceAnalysis-tinymce').setContent('$text'); tinymce.get('offenceAnalysis-tinymce').fire('blur'); return true") }
        fillPatternOfOffendingWith { text -> js.exec("tinymce.get('patternOfOffending-tinymce').setContent('$text'); tinymce.get('patternOfOffending-tinymce').fire('blur'); return true") }
    }
}
