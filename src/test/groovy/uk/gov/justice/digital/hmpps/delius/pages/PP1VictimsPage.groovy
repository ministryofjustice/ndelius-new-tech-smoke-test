package uk.gov.justice.digital.hmpps.delius.pages

import java.text.SimpleDateFormat

class PP1VictimsPage extends PP1BasePage {

    static at = { heading == "Victims" }

    static content = {
        fillVictimsImpactDetailsWith { text -> $("#victimsImpactDetails").value(text) }
        fillVictimsVLOContactDatesWith {
            text ->
                Date date = new SimpleDateFormat("dd/MM/yyyy").parse(text);

                $("#victimsVLOContactDate-day").value(new SimpleDateFormat("dd").format(date));
                $("#victimsVLOContactDate-month").value(new SimpleDateFormat("MM").format(date));
                $("#victimsVLOContactDate-year").value(new SimpleDateFormat("yyyy").format(date))
        }
        setVictimsSubmitVPSYes { $("#victimsSubmitVPS_yes").value("yes") }
        setVictimsEngagedInVCSYes { $("#victimsEngagedInVCS_yes").value("yes") }
    }
}
