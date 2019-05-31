package uk.gov.justice.digital.hmpps.delius.pages

class SFRPSOffenderAssessmentPage extends SFPSRBasePage {

    static at = { heading == "Offender assessment" }

    static content = {
        heading { $("h1").text() }
        accommodationCheckBox { $("#issueAccommodation") }
        fillAccommodationWith { text -> js.exec("tinymce.get('issueAccommodationDetails-tinymce').setContent('$text'); tinymce.get('issueAccommodationDetails-tinymce').fire('blur'); return true") }
        employmentCheckBox { $("#issueEmployment") }
        fillEmploymentWith { text -> js.exec("tinymce.get('issueEmploymentDetails-tinymce').setContent('$text'); tinymce.get('issueEmploymentDetails-tinymce').fire('blur'); return true") }
        financeCheckBox { $("#issueFinance") }
        fillFinanceWith { text -> js.exec("tinymce.get('issueFinanceDetails-tinymce').setContent('$text'); tinymce.get('issueFinanceDetails-tinymce').fire('blur'); return true") }
        relationshipsCheckBox { $("#issueRelationships") }
        fillRelationshipsWith { text -> js.exec("tinymce.get('issueRelationshipsDetails-tinymce').setContent('$text'); tinymce.get('issueRelationshipsDetails-tinymce').fire('blur'); return true") }
        substanceMisuseCheckBox { $("#issueSubstanceMisuse") }
        fillSubstanceMisuseWith { text -> js.exec("tinymce.get('issueSubstanceMisuseDetails-tinymce').setContent('$text'); tinymce.get('issueSubstanceMisuseDetails-tinymce').fire('blur'); return true") }
        healthCheckBox { $("#issueHealth") }
        fillHealthWith { text -> js.exec("tinymce.get('issueHealthDetails-tinymce').setContent('$text'); tinymce.get('issueHealthDetails-tinymce').fire('blur'); return true") }
        behaviourCheckBox { $("#issueBehaviour") }
        fillBehaviourWith { text -> js.exec("tinymce.get('issueBehaviourDetails-tinymce').setContent('$text'); tinymce.get('issueBehaviourDetails-tinymce').fire('blur'); return true") }
        otherCheckBox { $("#issueOther") }
        fillOtherWith { text -> js.exec("tinymce.get('issueOtherDetails-tinymce').setContent('$text'); tinymce.get('issueOtherDetails-tinymce').fire('blur'); return true") }
        setExperienceTraumaYes { $("#experienceTrauma_yes").value("yes") }
        fillExperienceTraumaDetailsWith { text -> js.exec("tinymce.get('experienceTraumaDetails-tinymce').setContent('$text'); tinymce.get('experienceTraumaDetails-tinymce').fire('blur'); return true") }
        setCaringResponsibilitiesYes { $("#caringResponsibilities_yes").value("yes") }
        fillCaringResponsibilitiesDetailsWith { text -> js.exec("tinymce.get('caringResponsibilitiesDetails-tinymce').setContent('$text'); tinymce.get('caringResponsibilitiesDetails-tinymce').fire('blur'); return true") }
    }
}
