package uk.gov.justice.digital.hmpps.delius.pages

class PP1BehaviourInPrisonPage extends PP1BasePage {

    static at = { heading == "Behaviour in prison" }

    static content = {
        fillBehaviourDetailWith { text -> $("#behaviourDetail .ql-editor").leftShift(text) }
        fillRotlSummaryWith { text -> $("#rotlSummary .ql-editor").leftShift(text) }
    }
}
