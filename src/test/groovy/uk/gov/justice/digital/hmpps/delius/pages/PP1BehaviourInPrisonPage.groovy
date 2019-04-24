package uk.gov.justice.digital.hmpps.delius.pages

class PP1BehaviourInPrisonPage extends PP1BasePage {

    static at = { heading == "Behaviour in prison" }

    static content = {
        fillBehaviourDetailWith { text -> js.exec("return tinymce.get('behaviourDetail').setContent('$text')") }
        fillRotlSummaryWith { text -> js.exec("return tinymce.get('rotlSummary').setContent('$text')") }
    }
}
