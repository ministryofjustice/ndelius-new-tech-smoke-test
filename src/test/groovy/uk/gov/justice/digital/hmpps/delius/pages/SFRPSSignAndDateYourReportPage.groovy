package uk.gov.justice.digital.hmpps.delius.pages

class SFRPSSignAndDateYourReportPage extends SFPSRBasePage {

    static content = {
        headingDisplayed { waitFor(5) { $("h1", text: "Sign and date your report") } }
        fillReportAuthorWith { text -> $("#reportAuthor").value(text) }
        fillOfficeWith { text -> $("#office").value(text) }
        fillCourtOfficePhoneNumberWith { text -> $("#courtOfficePhoneNumber").value(text) }
        fillCounterSignatureWith { text -> $("#counterSignature").value(text) }
        submitAndViewYourDocumentListButton { $("input[value='Submit and view your document list']") }
    }
}
