package uk.gov.justice.digital.hmpps.delius.pages

class PP1OPDPathwayPage extends PP1BasePage {

    static at = { heading == "OPD pathway" }

    static content = {
        setOPDPathwayServicesYes { $("#consideredForOPDPathwayServices_yes").value("yes") }
    }

}