package uk.gov.justice.digital.hmpps.delius.pages;

import geb.Page;

public class SFPSRBasePage extends Page {

    static content = {
        saveDraftLink { $("#exitLink") }
        viewDraftButton { $("#draftReport") }
        saveAndContinue { $("button" , text: "Continue") }
        offenderDetailsLink { $( "a", text: "Offender details") }
        feedbackLink { $( "a", text: "feedback") }

        visitedPages { $( "#visitedPages").value() }
        onBehalfOfUser { $( "#onBehalfOfUser").value() }
        entityId { $( "#entityId").value() }
        documentId { $( "#documentId").value() }
        pncSupplied { $( "#pncSupplied").value() }
        addressSupplied { $( "#addressSupplied").value() }

        name { $( "#name").value() }
        dateOfBirth { $( "#dateOfBirth").value() }
        age { $( "#age").value() }
        address { $( "#address").value() }
        crn { $( "#crn").value() }
        pnc { $( "#pnc").value() }

        court { $( "#court").value() }
        dateOfHearing { $( "#dateOfHearing").value() }
        localJusticeArea { $( "#localJusticeArea").value() }

        interviewInformationSource { $( "#interviewInformationSource").value() }
        serviceRecordsInformationSource { $( "#serviceRecordsInformationSource").value() }
        cpsSummaryInformationSource { $( "#cpsSummaryInformationSource").value() }
        oasysAssessmentsInformationSource { $( "#oasysAssessmentsInformationSource").value() }
        previousConvictionsInformationSource { $( "#previousConvictionsInformationSource").value() }
        victimStatementInformationSource { $( "#victimStatementInformationSource").value() }
        childrenServicesInformationSource { $( "#childrenServicesInformationSource").value() }
        policeInformationSource { $( "#policeInformationSource").value() }
        sentencingGuidelinesInformationSource { $( "#sentencingGuidelinesInformationSource").value() }
        otherInformationSource { $( "#otherInformationSource").value() }
        otherInformationDetails { $( "#otherInformationDetails").value() }

        fillMainOffenceWith { text -> $("#mainOffence .ql-editor").leftShift(text) }
        fillOtherOffencesWith { text -> $("#otherOffences .ql-editor").leftShift(text) }
        offenceSummary { $( "#offenceSummary").value() }

        offenceAnalysis { $( "#offenceAnalysis").value() }
        patternOfOffending { $( "#patternOfOffending").value() }

        issueAccommodation { $( "#issueAccommodation").value() }
        issueEmployment { $( "#issueEmployment").value() }
        issueFinance { $( "#issueFinance").value() }
        issueRelationships { $( "#issueRelationships").value() }
        issueSubstanceMisuse { $( "#issueSubstanceMisuse").value() }
        issueHealth { $( "#issueHealth").value() }
        issueBehaviour { $( "#issueBehaviour").value() }
        issueOther { $( "#issueOther").value() }

        issueAccommodationDetails { $( "#issueAccommodationDetails").value() }
        issueEmploymentDetails {$( "#issueEmploymentDetails").value() }
        issueFinanceDetails { $( "#issueFinanceDetails").value() }
        issueRelationshipsDetails { $( "#issueRelationshipsDetails").value() }
        issueSubstanceMisuseDetails { $( "#issueSubstanceMisuseDetails").value() }
        issueHealthDetails { $( "#issueHealthDetails").value() }
        issueBehaviourDetails { $( "#issueBehaviourDetails").value() }
        issueOtherDetails { $( "#issueOtherDetails").value() }
        experienceTrauma { $( "#experienceTrauma").value() }
        experienceTraumaDetails { $( "#experienceTraumaDetails").value() }
        caringResponsibilities { $( "#caringResponsibilities").value() }
        caringResponsibilitiesDetails { $( "#caringResponsibilitiesDetails").value() }

        likelihoodOfReOffending { $( "#likelihoodOfReOffending").value() }
        riskOfSeriousHarm { $( "#riskOfSeriousHarm").value() }
        previousSupervisionResponse { $( "#previousSupervisionResponse").value() }
        additionalPreviousSupervision { $( "#additionalPreviousSupervision").value() }

        proposal { $( "#proposal").value() }
        consideredQualityDiversity { $( "#consideredQualityDiversity").value() }


        reportAuthor { $( "#reportAuthor").value() }
        office { $( "#office").value() }
        reportDate { $( "#reportDate").value() }
        courtOfficePhoneNumber { $( "#courtOfficePhoneNumber").value() }
        counterSignature { $( "#counterSignature").value() }
        startDate { $( "#startDate").value() }
    }
}
