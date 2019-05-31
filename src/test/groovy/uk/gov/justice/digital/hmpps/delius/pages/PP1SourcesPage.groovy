package uk.gov.justice.digital.hmpps.delius.pages

class PP1SourcesPage extends PP1BasePage {

    static at = { heading == "Sources" }

    static content = {
        clickSourcesPreviousConvictions { $("#sourcesPreviousConvictions").click() }
        clickSourcesCPSDocuments { $("#sourcesCPSDocuments").click() }
        clickSourcesJudgesComments { $("#sourcesJudgesComments").click() }
        clickSourcesParoleDossier { $("#sourcesParoleDossier").click() }
        clickSourcesPreviousParoleReports { $("#sourcesPreviousParoleReports").click() }
        clickSourcesPreSentenceReport { $("#sourcesPreSentenceReport").click() }
        clickSourcesProbationCaseRecord { $("#sourcesProbationCaseRecord").click() }
        clickSourcesOther { $("#sourcesOther").click() }
        fillSourcesOtherDetailWith { text -> js.exec("tinymce.get('sourcesOtherDetail-tinymce').setContent('$text'); tinymce.get('sourcesOtherDetail-tinymce').fire('blur'); return true") }
        fillSourcesAssessmentListWith { text -> js.exec("tinymce.get('sourcesAssessmentList-tinymce').setContent('$text'); tinymce.get('sourcesAssessmentList-tinymce').fire('blur'); return true") }
        setSourceLimitationsYes { $("#sourceLimitations_yes").value("yes") }
        fillSourceLimitationsDetailWith { text -> js.exec("tinymce.get('sourceLimitationsDetail-tinymce').setContent('$text'); tinymce.get('sourceLimitationsDetail-tinymce').fire('blur'); return true") }
    }
}
