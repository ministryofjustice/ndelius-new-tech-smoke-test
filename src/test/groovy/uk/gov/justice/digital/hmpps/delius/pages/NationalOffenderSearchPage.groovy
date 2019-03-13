package uk.gov.justice.digital.hmpps.delius.pages

import geb.Page

class NationalOffenderSearchPage extends Page {

    static content = {
        searchTerms { $("input[name=searchTerms]") }
        enterSearchTerms {searchTerm -> searchTerms.value(searchTerm)}
        resultCount { $(".qa-offender-details-row").size() }
        zeroResultsFound {$("h2 span", text: "0 results found")}
        offenders {$(".qa-offender-details-row").collect {
            it.text()
         }}
        hasResults {$("#offender-results") }
        feedbackLink {$("a", text: "feedback")}
        suggestionsFor {someText -> $("a", text: someText)}
        selectProviderFilter {area1, area2 -> $("input[value=${area1}]").click(); $("input[value=${area2}]").click(); }
        selectAnyInMyProviderFilter {$("#filters-my-providers input[type='checkbox']").first().click()}
        selectNorthEastInMyProviderFilter {$("#filters-my-providers input[value='N02']").first().click()}
        selectAnyInOtherProviderFilter {$("#filters-all-providers input[type='checkbox']").first().click()}
        deselectAllMyProvidersSelectedFilters(required: false) {
            $("#filters-my-providers input[type='checkbox']").forEach({checkbox -> checkbox.value(false)})
        }
        deselectAllOtherProvidersSelectedFilters(required: false) {
            $("#filters-all-providers input[type='checkbox']").forEach({checkbox -> checkbox.value(false)})
        }
        myProvidersFilter { $("#filters-my-providers") }
        otherProvidersFilter { $("#filters-all-providers") }
        selectMatchAllTerms(required: false) { $("#match-all-terms-yes").click() }
        deselectMatchAllTerms(required: false) { $("#match-all-terms-no").click() }
        matchAllTerms(required: false) { $("#match-all-terms-no").value() }
    }
}
