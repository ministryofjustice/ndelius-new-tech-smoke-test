package uk.gov.justice.digital.hmpps.delius.pages

import java.text.SimpleDateFormat

class SFRPSSignYourReportPage extends SFPSRBasePage {

    static at = { heading == "Sign your report" }

    static content = {
        heading { $("h1").text() }
        fillReportAuthorWith { text -> $("#reportAuthor").value(text) }
        fillOfficeWith { text -> $("#office").value(text) }
        fillCourtOfficePhoneNumberWith { text -> $("#courtOfficePhoneNumber").value(text) }
        fillCounterSignatureWith { text -> $("#counterSignature").value(text) }
        fillReportDateWith {
            text ->
                Date date = new SimpleDateFormat("dd/MM/yyyy").parse(text);

                $("#reportDate-day").value(new SimpleDateFormat("dd").format(date));
                $("#reportDate-month").value(new SimpleDateFormat("MM").format(date));
                $("#reportDate-year").value(new SimpleDateFormat("yyyy").format(date))
        }
        submitButton { $("button", text: "Submit") }

    }
}
