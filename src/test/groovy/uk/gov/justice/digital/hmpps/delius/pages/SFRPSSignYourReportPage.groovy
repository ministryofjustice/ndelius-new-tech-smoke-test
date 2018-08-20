package uk.gov.justice.digital.hmpps.delius.pages

class SFRPSSignYourReportPage extends SFPSRBasePage {

    static at = { heading == "Sign your report" }

    static content = {
        heading { $("h1").text() }
        fillReportAuthorWith { text -> $("#reportAuthor").value(text) }
        fillOfficeWith { text -> $("#office").value(text) }
        fillCourtOfficePhoneNumberWith { text -> $("#courtOfficePhoneNumber").value(text) }
        fillCounterSignatureWith { text -> $("#counterSignature").value(text) }
        submitButton { $("button", text: "Submit") }

    }
}
