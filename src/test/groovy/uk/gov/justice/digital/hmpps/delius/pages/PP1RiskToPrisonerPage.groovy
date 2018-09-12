package uk.gov.justice.digital.hmpps.delius.pages

class PP1RiskToPrisonerPage extends PP1BasePage {
    static at = { heading == "Risk to the prisoner" }

    static content = {
        setSelfHarmCommunityYes { $("#selfHarmCommunity_yes").value("yes") }
        setSelfHarmCustodyYes { $("#selfHarmCustody_yes").value("yes") }
        setOthersHarmCommunityYes { $("#othersHarmCommunity_yes").value("yes") }
        setOthersHarmCustodyYes { $("#othersHarmCustody_yes").value("yes") }
    }
}
