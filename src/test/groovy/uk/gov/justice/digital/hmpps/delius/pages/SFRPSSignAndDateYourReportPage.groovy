package uk.gov.justice.digital.hmpps.delius.pages

class SFRPSSignAndDateYourReportPage extends SFPSRBasePage {

    static at = { heading == "Sign and date your report" }

    static content = {
        heading { $("h1").text() }
        fillReportAuthorWith { text -> $("#reportAuthor").value(text) }
        fillOfficeWith { text -> $("#office").value(text) }
        fillCourtOfficePhoneNumberWith { text -> $("#courtOfficePhoneNumber").value(text) }
        fillCounterSignatureWith { text -> $("#counterSignature").value(text) }
        submitAndViewYourDocumentListButton { $("input[value='Submit and view your document list']") }
    }
}
