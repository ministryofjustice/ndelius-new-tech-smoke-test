package uk.gov.justice.digital.hmpps.delius.pages

import geb.Page

class SFRPSOffenderDetailsPage extends Page {

    static content = {
        saveDraftLink {$("#exitLink") }
    }
}
