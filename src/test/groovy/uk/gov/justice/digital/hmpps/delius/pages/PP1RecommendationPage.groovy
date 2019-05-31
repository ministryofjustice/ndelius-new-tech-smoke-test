package uk.gov.justice.digital.hmpps.delius.pages

class PP1RecommendationPage extends PP1BasePage {

    static at = { heading == "Recommendation" }

    static content = {
        fillRecommendationWith { text -> js.exec("tinymce.get('recommendation-tinymce').setContent('$text'); tinymce.get('recommendation-tinymce').fire('blur'); return true") }
    }

}