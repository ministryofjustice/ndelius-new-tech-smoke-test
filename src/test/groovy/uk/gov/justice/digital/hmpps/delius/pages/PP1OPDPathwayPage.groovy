package uk.gov.justice.digital.hmpps.delius.pages

import java.text.SimpleDateFormat

class PP1OPDPathwayPage extends PP1BasePage {

    static at = { heading == "OPD pathway" }

    static content = {
        setOPDPathwayServicesYes { $("#consideredForOPDPathwayServices_yes").value("yes") }
        fillScreenedDateWith {
            text ->
                Date date = new SimpleDateFormat("dd/MM/yyyy").parse(text);
                $("#opdScreenedDate-day").value(new SimpleDateFormat("dd").format(date));
                $("#opdScreenedDate-month").value(new SimpleDateFormat("MM").format(date));
                $("#opdScreenedDate-year").value(new SimpleDateFormat("yyyy").format(date))
        }
    }
}
