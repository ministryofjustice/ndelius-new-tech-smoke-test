package uk.gov.justice.digital.hmpps.delius.pages

import java.text.SimpleDateFormat

class PP1MappaPage extends PP1BasePage {
    static at = { heading == "Multi Agency Public Protection Arrangements (MAPPA)" }

    static content = {
        setEligibleForMappaYes { $("#eligibleForMappa_yes").value("yes") }
        fillScreenedDateWith {
            text ->
                Date date = new SimpleDateFormat("dd/MM/yyyy").parse(text);

                $("#mappaScreenedDate-day").value(new SimpleDateFormat("dd").format(date));
                $("#mappaScreenedDate-month").value(new SimpleDateFormat("MM").format(date));
                $("#mappaScreenedDate-year").value(new SimpleDateFormat("yyyy").format(date))
        }
        setMappaCategory1 { $("#mappaCategory_1").value("1") }
        setMappaLevel2 { $("#mappaLevel_2").value("2") }
    }

}
