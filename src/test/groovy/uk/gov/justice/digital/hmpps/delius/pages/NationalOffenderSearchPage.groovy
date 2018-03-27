package uk.gov.justice.digital.hmpps.delius.pages

import geb.Page

class NationalOffenderSearchPage extends Page {

    static content = {
        searchTerms { $("input[name=searchTerms]") }
        enterSearchTerms {searchTerm -> searchTerms.value(searchTerm)}
        resultCount { $(".offenderDetailsRow").size() }
        offenders {$(".offenderDetailsRow").collect {
            it.text()
         }}
        hasResults {$("#offender-results") }
        feedbackLink {$("a", text: "feedback")}
        suggestionsFor {someText -> $("a", text: someText)}
    }
}
