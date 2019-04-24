package uk.gov.justice.digital.hmpps.delius.pages

class SFRPSOffenderAssessmentPage extends SFPSRBasePage {

    static at = { heading == "Offender assessment" }

    static content = {
        heading { $("h1").text() }
        accommodationCheckBox { $("#issueAccommodation") }
        fillAccommodationWith { text -> js.exec("return tinymce.get('issueAccommodationDetails').setContent('$text')") }
        employmentCheckBox { $("#issueEmployment") }
        fillEmploymentWith { text -> js.exec("return tinymce.get('issueEmploymentDetails').setContent('$text')") }
        financeCheckBox { $("#issueFinance") }
        fillFinanceWith { text -> js.exec("return tinymce.get('issueFinanceDetails').setContent('$text')") }
        relationshipsCheckBox { $("#issueRelationships") }
        fillRelationshipsWith { text -> js.exec("return tinymce.get('issueRelationshipsDetails').setContent('$text')") }
        substanceMisuseCheckBox { $("#issueSubstanceMisuse") }
        fillSubstanceMisuseWith { text -> js.exec("return tinymce.get('issueSubstanceMisuseDetails').setContent('$text')") }
        healthCheckBox { $("#issueHealth") }
        fillHealthWith { text -> js.exec("return tinymce.get('issueHealthDetails').setContent('$text')") }
        behaviourCheckBox { $("#issueBehaviour") }
        fillBehaviourWith { text -> js.exec("return tinymce.get('issueBehaviourDetails').setContent('$text')") }
        otherCheckBox { $("#issueOther") }
        fillOtherWith { text -> js.exec("return tinymce.get('issueOtherDetails').setContent('$text')") }
        setExperienceTraumaYes { $("#experienceTrauma_yes").value("yes") }
        fillExperienceTraumaDetailsWith { text -> js.exec("return tinymce.get('experienceTraumaDetails').setContent('$text')") }
        setCaringResponsibilitiesYes { $("#caringResponsibilities_yes").value("yes") }
        fillCaringResponsibilitiesDetailsWith { text -> js.exec("return tinymce.get('caringResponsibilitiesDetails').setContent('$text')") }

    }
}
