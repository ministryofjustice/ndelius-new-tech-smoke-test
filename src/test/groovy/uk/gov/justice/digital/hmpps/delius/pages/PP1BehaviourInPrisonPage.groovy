package uk.gov.justice.digital.hmpps.delius.pages

class PP1BehaviourInPrisonPage extends PP1BasePage {

    static at = { heading == "Behaviour in prison" }

    static content = {
        fillBehaviourDetailWith { text -> js.exec("tinymce.get('behaviourDetail-tinymce').setContent('$text'); tinymce.get('behaviourDetail-tinymce').fire('blur'); return true") }
        fillRotlSummaryWith { text -> js.exec("tinymce.get('rotlSummary-tinymce').setContent('$text'); tinymce.get('rotlSummary-tinymce').fire('blur'); return true") }
    }
}
