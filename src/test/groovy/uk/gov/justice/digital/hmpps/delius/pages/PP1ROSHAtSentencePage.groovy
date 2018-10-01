package uk.gov.justice.digital.hmpps.delius.pages

import java.text.SimpleDateFormat

class PP1ROSHAtSentencePage extends PP1BasePage {

    static at = { heading == "RoSH at point of sentence" }

    static content = {
        setRoshAtPointOfSentenceYes { $("#roshAtPosAssessmentCompleted_yes").value("yes") }
        fillRoshAtPointOfSentenceDateWith {
            text ->
                Date date = new SimpleDateFormat("MM/yyyy").parse(text);

                $("#roshAtPosDate-month").value(new SimpleDateFormat("MM").format(date));
                $("#roshAtPosDate-year").value(new SimpleDateFormat("yyyy").format(date))
        }
        setRoshAtPointOfSentencePublicLow { $("#roshAtPosPublic_low").value("low") }
        setRoshAtPointOfSentenceKnownAdultLow { $("#roshAtPosKnownAdult_low").value("low") }
        setRoshAtPointOfSentenceChildrenLow { $("#roshAtPosChildren_low").value("low") }
        setRoshAtPointOfSentencePrisonersLow { $("#roshAtPosPrisoners_low").value("low") }
        setRoshAtPointOfSentenceStaffLow { $("#roshAtPosStaff_low").value("low") }

        fillRoshAtPointOfSentenceIndexWith { text -> $("#roshAtPosAttitudeIndexOffence .ql-editor").leftShift(text) }
        fillRoshAtPointOfSentencePreviousWith { text -> $("#roshAtPosAttitudePreviousOffending .ql-editor").leftShift(text) }
    }
}
