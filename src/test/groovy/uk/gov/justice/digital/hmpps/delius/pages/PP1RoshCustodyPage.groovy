package uk.gov.justice.digital.hmpps.delius.pages

class PP1RoshCustodyPage extends PP1BasePage {
    static at = { heading == "Current RoSH: custody" }

    static content = {
        setRoshCustodyPublicLow { $("#roshCustodyPublic_low").value("low") }
        setRoshCustodyKnownAdultMedium { $("#roshCustodyKnownAdult_medium").value("medium") }
        setRoshCustodyChildrenHigh { $("#roshCustodyChildren_high").value("high") }
        setRoshCustodyPrisonersVeryHigh { $("#roshCustodyPrisoners_very_high").value("very_high") }
        setRoshCustodyStaffLow { $("#roshCustodyStaff_low").value("low") }
    }
}
