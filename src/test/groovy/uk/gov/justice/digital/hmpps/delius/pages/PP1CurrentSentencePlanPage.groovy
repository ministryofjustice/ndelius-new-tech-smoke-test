package uk.gov.justice.digital.hmpps.delius.pages

class PP1CurrentSentencePlanPage extends PP1BasePage {

    static at = { heading == "Prison sentence plan and response" }

    static content = {
        fillSentencePlanWith { text -> $("#sentencePlan").value(text) }
    }
}
