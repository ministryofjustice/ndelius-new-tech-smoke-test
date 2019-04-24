package uk.gov.justice.digital.hmpps.delius.pages

class PP1RecommendationPage extends PP1BasePage {

    static at = { heading == "Recommendation" }

    static content = {
        fillRecommendationWith { text -> js.exec("return tinymce.get('recommendation').setContent('$text')") }
    }

}