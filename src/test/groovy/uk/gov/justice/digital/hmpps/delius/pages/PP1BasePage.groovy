package uk.gov.justice.digital.hmpps.delius.pages;

import geb.Page;

public class PP1BasePage extends Page {

    static content = {
        saveDraftLink { $("#exitLink") }
        saveAndContinue { $("button" , text: "Continue") }

        visitedPages { $( "#visitedPages").value() }
        onBehalfOfUser { $( "#onBehalfOfUser").value() }
        entityId { $( "#entityId").value() }
        documentId { $( "#documentId").value() }
    }
}
