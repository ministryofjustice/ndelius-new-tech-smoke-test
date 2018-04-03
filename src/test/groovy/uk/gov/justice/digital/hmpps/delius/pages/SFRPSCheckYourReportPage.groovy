package uk.gov.justice.digital.hmpps.delius.pages

import geb.Page

class SFRPSCheckYourReportPage extends Page {

    static content = {
        headingDisplayed { waitFor(5) { $("h1", text: "Check your report") } }
        signAndDateYourReportButton { $("input[value='Sign and date your report']") }
    }
}
