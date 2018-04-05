package uk.gov.justice.digital.hmpps.delius.pages

import geb.Page

class SFRPSDraftSavedPage extends Page {

    static content = {
        documentListLink {$("#document-list a") }
    }
}
