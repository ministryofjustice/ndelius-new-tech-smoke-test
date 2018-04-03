package uk.gov.justice.digital.hmpps.delius.pages;

import geb.Page;

public class SFPSRBasePage extends Page {

    static content = {
        saveDraftLink { $("#exitLink") }
        saveAndContinue { $("input[value='Save & continue']") }
        offenderDetailsLink { $( "a", text: "Offender details") }
        feedbackLink { $( "a", text: "feedback") }
    }

}
