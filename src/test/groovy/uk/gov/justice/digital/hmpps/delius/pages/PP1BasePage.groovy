package uk.gov.justice.digital.hmpps.delius.pages;

import geb.Page;

public class PP1BasePage extends Page {

    static content = {
        heading { $("h1").text() }

        saveDraftLink { $("#exitLink") }
        saveAndContinue { $("button" , text: "Continue") }

        visitedPages { $( "#visitedPages").value() }
        onBehalfOfUser { $( "#onBehalfOfUser").value() }
        entityId { $( "#entityId").value() }
        documentId { $( "#documentId").value() }

        prisonerContactDetail { $( "#prisonerContactDetail").value() }
        prisonerContactFamilyDetail { $( "#prisonerContactFamilyDetail").value() }
        prisonerContactAgenciesDetail { $( "#prisonerContactAgenciesDetail").value() }

        victimsImpactDetails  { $( "#victimsImpactDetails").value() }
        victimsVLOContactDate  { $( "#victimsVLOContactDate").value() }
        victimsVLOContactDate_day  { $( "#victimsVLOContactDate_day").value() }
        victimsVLOContactDate_month  { $( "#victimsVLOContactDate_month").value() }
        victimsVLOContactDate_year  { $( "#victimsVLOContactDate_year").value() }
        victimsEngagedInVCS  { $( "#victimsEngagedInVCS").value() }
        victimsSubmitVPS  { $( "#victimsSubmitVPS").value() }

        consideredForOPDPathwayServices  { $( "#consideredForOPDPathwayServices").value() }

        interventionsDetail { $( "#interventionsDetail").value() }
        interventionsSummary { $( "#interventionsSummary").value() }

        supervisionPlanRequired{ $( "#supervisionPlanRequired").value() }
        supervisionPlanDetail{ $( "#supervisionPlanDetail").value() }

        recommendation { $( "#recommendation").value() }

        sourcesPreviousConvictions { $( "#sourcesPreviousConvictions").value() }
        sourcesCPSDocuments { $( "#sourcesCPSDocuments").value() }
        sourcesJudgesComments { $( "#sourcesJudgesComments").value() }
        sourcesParoleDossier { $( "#sourcesParoleDossier").value() }
        sourcesPreviousParoleReports { $( "#sourcesPreviousParoleReports").value() }
        sourcesPreSentenceReport { $( "#sourcesPreSentenceReport").value() }
        sourcesProbationCaseRecord { $( "#sourcesProbationCaseRecord").value() }
        sourcesOther { $( "#sourcesOther").value() }
        sourcesOtherDetail { $( "#sourcesOtherDetail").value() }
        sourcesAssessmentList { $( "#sourcesAssessmentList").value() }
        sourceLimitations { $( "#sourceLimitations").value() }
        sourceLimitationsDetail { $( "#sourceLimitationsDetail").value() }
    }
}
