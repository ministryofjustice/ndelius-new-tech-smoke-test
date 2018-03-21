package uk.gov.justice.digital.hmpps.delius.pages

import geb.Page

class NationalOffenderSearchPage extends Page {

    static content = {
        searchTerms { $("input[name=searchTerms]") }
    }
}
