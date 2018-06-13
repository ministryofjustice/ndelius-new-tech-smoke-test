package uk.gov.justice.digital.hmpps.delius.pages

class SFRPSOffenderAssessmentPage extends SFPSRBasePage {

    static at = { heading == "Offender assessment" }

    static content = {
        heading { $("h1").text() }
        accommodationCheckBox { $("#issueAccommodation") }
        fillAccommodationWith { text -> $("#issueAccommodationDetails .ql-editor").leftShift(text) }
        employmentCheckBox { $("#issueEmployment") }
        fillEmploymentWith { text -> $("#issueEmploymentDetails .ql-editor").leftShift(text)  }
        financeCheckBox { $("#issueFinance") }
        fillFinanceWith{ text -> $("#issueFinanceDetails .ql-editor").leftShift(text)  }
        relationshipsCheckBox { $("#issueRelationships") }
        fillRelationshipsWith{ text -> $("#issueRelationshipsDetails .ql-editor").leftShift(text)  }
        substanceMisuseCheckBox { $("#issueSubstanceMisuse") }
        fillSubstanceMisuseWith{ text -> $("#issueSubstanceMisuseDetails .ql-editor").leftShift(text) }
        healthCheckBox { $("#issueHealth") }
        fillHealthWith{ text -> $("#issueHealthDetails .ql-editor").leftShift(text) }
        behaviourCheckBox { $("#issueBehaviour") }
        fillBehaviourWith { text -> $("#issueBehaviourDetails .ql-editor").leftShift(text) }
        otherCheckBox { $("#issueOther") }
        fillOtherWith { text -> $("#issueOtherDetails .ql-editor").leftShift(text) }
        setExperienceTraumaYes { $( "#experienceTrauma_yes").value("yes") }
        fillExperienceTraumaDetailsWith { text -> $("#experienceTraumaDetails .ql-editor").leftShift(text) }
        setCaringResponsibilitiesYes { $( "#caringResponsibilities_yes").value("yes") }
        fillCaringResponsibilitiesDetailsWith { text -> $("#caringResponsibilitiesDetails .ql-editor").leftShift(text) }

    }
}
