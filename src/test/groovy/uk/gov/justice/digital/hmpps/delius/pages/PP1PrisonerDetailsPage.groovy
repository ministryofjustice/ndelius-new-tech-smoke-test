package uk.gov.justice.digital.hmpps.delius.pages

class PP1PrisonerDetailsPage extends PP1BasePage {

    static at = { heading == "Prisoner details" }

    static content = {
        setPrisonerDetailsPrisonInstitution { text -> $("#prisonerDetailsPrisonInstitution").value(text) }
        setPrisonerDetailsPrisonersFullName { text -> $("#prisonerDetailsPrisonersFullName").value(text) }
        setPrisonerDetailsPrisonNumber { text -> $("#prisonerDetailsPrisonNumber").value(text) }
        setPrisonerDetailsNomisNumber { text -> $("#prisonerDetailsNomisNumber").value(text) }

        setPrisonerDetailsPrisonersCategoryA { $("#prisonerDetailsPrisonersCategory_a").value("a") }

        fillPrisonerDetailOffenceWith { text -> $("#prisonerDetailsOffence .ql-editor").leftShift(text) }
        fillPrisonerDetailSentenceWith { text -> $("#prisonerDetailsSentence .ql-editor").leftShift(text) }

        setPrisonerDetailsSentenceTypeDeterminate { $("#prisonerDetailsSentenceType_determinate").value("determinate") }
    }

}
