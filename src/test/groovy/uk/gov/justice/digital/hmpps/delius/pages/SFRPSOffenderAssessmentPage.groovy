package uk.gov.justice.digital.hmpps.delius.pages

class SFRPSOffenderAssessmentPage extends SFPSRBasePage {

    static content = {
        headingDisplayed { waitFor(5) { $("h1", text: "Offender assessment") } }
        accommodationCheckBox { $("#issueAccommodation") }
        fillAccommodationWith { text -> $("#issueAccommodationDetails").value(text) }
        employmentCheckBox { $("#issueEmployment") }
        fillEmploymentWith { text -> $("#issueEmploymentDetails").value(text)  }
        financeCheckBox { $("#issueFinance") }
        fillFinanceWith{ text -> $("#issueFinanceDetails").value(text)  }
        relationshipsCheckBox { $("#issueRelationships") }
        fillRelationshipsWith{ text -> $("#issueRelationshipsDetails").value(text)  }
        substanceMisuseCheckBox { $("#issueSubstanceMisuse") }
        fillSubstanceMisuseWith{ text -> $("#issueSubstanceMisuseDetails").value(text) }
        healthCheckBox { $("#issueHealth") }
        fillHealthWith{ text -> $("#issueHealthDetails").value(text) }
        behaviourCheckBox { $("#issueBehaviour") }
        fillBehaviourWith { text -> $("#issueBehaviourDetails").value(text) }
        otherCheckBox { $("#issueOther") }
        fillOtherWith { text -> $("#issueOtherDetails").value(text) }
    }
}
