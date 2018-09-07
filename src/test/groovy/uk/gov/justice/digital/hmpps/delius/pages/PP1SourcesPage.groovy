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
        fillSourcesOtherDetailWith { text -> $("#sourcesOtherDetail .ql-editor").leftShift(text) }
        fillSourcesAssessmentListWith { text -> $("#sourcesAssessmentList .ql-editor").leftShift(text) }
        setSourceLimitationsYes { $("#sourceLimitations_yes").value("yes") }
        fillSourceLimitationsDetailWith { text -> $("#sourceLimitationsDetail .ql-editor").leftShift(text) }
    }
}
