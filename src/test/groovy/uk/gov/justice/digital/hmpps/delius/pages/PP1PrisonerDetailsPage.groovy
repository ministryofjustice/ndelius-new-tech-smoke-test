package uk.gov.justice.digital.hmpps.delius.pages

class PP1PrisonerDetailsPage extends PP1BasePage {

    static at = { heading == "Prisoner details" }

    static content = {
        setPrisonerDetailsPrisonersCategoryA { $("#prisonerDetailsPrisonersCategory_a").value("a") }

        fillPrisonerDetailOffenceWith { text -> $("#prisonerDetailsOffence .ql-editor").leftShift(text) }
        fillPrisonerDetailSentenceWith { text -> $("#prisonerDetailsSentence .ql-editor").leftShift(text) }

        setPrisonerDetailsSentenceTypeDeterminate { $("#prisonerDetailsSentenceType_determinate").value("determinate") }
    }

}
