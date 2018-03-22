package uk.gov.justice.digital.hmpps.delius.pages

import geb.Page

class SFRPSWelcomePage extends Page {

    static content = {
        startNowButton {$("input[value='Start now']") }

    }
}
