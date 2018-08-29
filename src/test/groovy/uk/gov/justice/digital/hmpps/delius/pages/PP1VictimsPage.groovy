package uk.gov.justice.digital.hmpps.delius.pages

class PP1VictimsPage extends PP1BasePage {

    static at = { heading == "Victims" }

    static content = {
        fillVictimsImpactDetailsWith { text -> $("#victimsImpactDetails .ql-editor").leftShift(text) }
        fillVictimsVLOContactDatesWith { text -> $("#victimsVLOContactDate").value(text) }
        setVictimsSubmitVPSYes { $("#victimsSubmitVPS_yes").value("yes") }
        setVictimsEngagedInVCSYes { $("#victimsEngagedInVCS_yes").value("yes") }
    }
}
