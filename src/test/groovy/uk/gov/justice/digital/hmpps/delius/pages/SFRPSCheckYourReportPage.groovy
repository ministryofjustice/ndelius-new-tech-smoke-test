package uk.gov.justice.digital.hmpps.delius.pages

import geb.Page

class SFRPSCheckYourReportPage extends Page {

    static at = { heading ==  "Check your report" }

    static content = {
        heading { $("h1").text() }
        signAndDateYourReportButton { $("input[value='Sign your report']") }
    }
}
