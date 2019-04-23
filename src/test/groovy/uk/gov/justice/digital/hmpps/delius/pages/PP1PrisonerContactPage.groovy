package uk.gov.justice.digital.hmpps.delius.pages

class PP1PrisonerContactPage extends PP1BasePage {

    static at = { heading == "Prisoner contact" }

    static content = {
        fillPrisonerContactDetailWith { text -> $("#prisonerContactDetail").value(text) }
        fillPrisonerContactFamilyDetailWith { text -> $("#prisonerContactFamilyDetail").value(text) }
        fillPrisonerContactAgenciesDetailWith { text -> $("#prisonerContactAgenciesDetail").value(text) }
    }
}
