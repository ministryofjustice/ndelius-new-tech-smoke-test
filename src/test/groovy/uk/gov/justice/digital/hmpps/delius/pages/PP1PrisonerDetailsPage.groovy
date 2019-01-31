package uk.gov.justice.digital.hmpps.delius.pages

import java.text.SimpleDateFormat

class PP1PrisonerDetailsPage extends PP1BasePage {

    static at = { heading == "Prisoner details" }

    static content = {
        setPrisonerDetailsPrisonersCategoryA { $("#prisonerDetailsPrisonersCategory_a").value("a") }

        fillPrisonerDetailOffenceWith { text -> $("#prisonerDetailsOffence .ql-editor").leftShift(text) }
        fillPrisonerDetailSentenceWith { text -> $("#prisonerDetailsSentence .ql-editor").leftShift(text) }

        setPrisonerDetailsDeterminate { $("#prisonerDetailsSentenceType_determinate").value("determinate") }
        setPrisonerDetailsDeterminateSentenceType { $("#prisonerDetailsDeterminateSentenceType_discretionaryConditionalRelease").value("discretionaryConditionalRelease") }

        fillPrisonerDetailsDeterminateReleaseDateDatesWith {
            text ->
                Date date = new SimpleDateFormat("dd/MM/yyyy").parse(text);

                $("#prisonerDetailsParoleEligibilityDate-day").value(new SimpleDateFormat("dd").format(date));
                $("#prisonerDetailsParoleEligibilityDate-month").value(new SimpleDateFormat("MM").format(date));
                $("#prisonerDetailsParoleEligibilityDate-year").value(new SimpleDateFormat("yyyy").format(date))
        }
    }

}
