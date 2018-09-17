package uk.gov.justice.digital.hmpps.delius.pages

class PP1RoshCommunityPage extends PP1BasePage {
    static at = { heading == "Current RoSH: community" }

    static content = {
        setRoshCommunityPublicLow { $("#roshCommunityPublic_low").value("low") }
        setRoshCommunityKnownAdultMedium { $("#roshCommunityKnownAdult_medium").value("medium") }
        setRoshCommunityChildrenHigh { $("#roshCommunityChildren_high").value("high") }
        setRoshCommunityPrisonersVeryHigh { $("#roshCommunityPrisoners_very_high").value("very_high") }
        setRoshCommunityStaffLow { $("#roshCommunityStaff_low").value("low") }
    }
}
