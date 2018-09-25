package uk.gov.justice.digital.hmpps.delius.pages

class PP1CheckYourReportPage extends PP1BasePage {

    static at = { heading == "Check your report" }

    static content = {
        signYourReportButton { $("button", text: "Sign") }
    }

}