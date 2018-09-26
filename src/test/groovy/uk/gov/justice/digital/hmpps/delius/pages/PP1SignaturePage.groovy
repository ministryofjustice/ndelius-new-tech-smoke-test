package uk.gov.justice.digital.hmpps.delius.pages

import java.text.SimpleDateFormat

class PP1SignaturePage extends PP1BasePage {
    static at = { heading == "Signature & date" }

    static content = {
        setSignatureName { text -> $("#signatureName").value(text) }
        setSignatureDivision { text -> $("#signatureDivision").value(text) }
        setSignatureOfficeAddress { text -> $("#signatureOfficeAddress").value(text) }
        setSignatureEmail { text -> $("#signatureEmail").value(text) }
        setSignatureTelephone { text -> $("#signatureTelephone").value(text) }
        setSignatureCounterName { text -> $("#signatureCounterName").value(text) }
        setSignatureCounterRole { text -> $("#signatureCounterRole").value(text) }
        fillSignatureDateWith {
            text ->
                Date date = new SimpleDateFormat("dd/MM/yyyy").parse(text)

                $("#signatureDate-day").value(new SimpleDateFormat("dd").format(date))
                $("#signatureDate-month").value(new SimpleDateFormat("MM").format(date))
                $("#signatureDate-year").value(new SimpleDateFormat("yyyy").format(date))
        }
        submitReport { $("button" , text: "Submit") }
    }
}
