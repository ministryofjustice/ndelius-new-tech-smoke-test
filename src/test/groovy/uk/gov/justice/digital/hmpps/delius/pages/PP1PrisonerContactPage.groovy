package uk.gov.justice.digital.hmpps.delius.pages

class PP1PrisonerContactPage extends PP1BasePage {

    static at = { heading == "Prisoner contact" }

    static content = {
        fillPrisonerContactDetailWith { text -> js.exec("return tinymce.get('prisonerContactDetail').setContent('$text')") }
        fillPrisonerContactFamilyDetailWith { text -> js.exec("return tinymce.get('prisonerContactFamilyDetail').setContent('$text')") }
        fillPrisonerContactAgenciesDetailWith { text -> js.exec("return tinymce.get('prisonerContactAgenciesDetail').setContent('$text')") }
    }
}
