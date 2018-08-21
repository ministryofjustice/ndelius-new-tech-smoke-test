package uk.gov.justice.digital.hmpps.delius.pages

class PP1PrisonerContactPage extends PP1BasePage {

    static at = { heading == "Prisoner contact" }

    static content = {
        fillPrisonerContactDetailWith { text -> $("#prisonerContactDetail .ql-editor").leftShift(text) }
        fillPrisonerContactFamilyDetailWith { text -> $("#prisonerContactFamilyDetail .ql-editor").leftShift(text) }
        fillPrisonerContactAgenciesDetailWith { text -> $("#prisonerContactAgenciesDetail .ql-editor").leftShift(text) }
    }
}
