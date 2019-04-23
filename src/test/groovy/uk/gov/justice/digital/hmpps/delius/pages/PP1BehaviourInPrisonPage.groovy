package uk.gov.justice.digital.hmpps.delius.pages

class PP1BehaviourInPrisonPage extends PP1BasePage {

    static at = { heading == "Behaviour in prison" }

    static content = {
        fillBehaviourDetailWith { text -> $("#behaviourDetail").value(text) }
        fillRotlSummaryWith { text -> $("#rotlSummary").value(text) }
    }
}
