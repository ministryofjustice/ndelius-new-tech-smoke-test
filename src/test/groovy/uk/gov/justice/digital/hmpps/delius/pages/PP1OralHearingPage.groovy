package uk.gov.justice.digital.hmpps.delius.pages

class PP1OralHearingPage extends PP1BasePage {

    static at = { heading == "Oral hearing" }

    static content = {
        fillOralHearingWith { text -> $("#oralHearing").value(text) }
    }

}