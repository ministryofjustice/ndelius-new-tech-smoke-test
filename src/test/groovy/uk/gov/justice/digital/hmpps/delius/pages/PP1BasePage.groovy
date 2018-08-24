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

        consideredForOPDPathwayServices  { $( "#consideredForOPDPathwayServices").value() }


        interventionsDetail { $( "#interventionsDetail").value() }
        interventionsSummary { $( "#interventionsSummary").value() }
    }
}
