package uk.gov.justice.digital.hmpps.delius.pages

class PP1PrisonerContactPage extends PP1BasePage {

    static at = { heading == "Prisoner contact" }

    static content = {
        fillPrisonerContactDetailWith { text -> js.exec("tinymce.get('prisonerContactDetail-tinymce').setContent('$text'); tinymce.get('prisonerContactDetail-tinymce').fire('blur'); return true") }
        fillPrisonerContactFamilyDetailWith { text -> js.exec("tinymce.get('prisonerContactFamilyDetail-tinymce').setContent('$text'); tinymce.get('prisonerContactFamilyDetail-tinymce').fire('blur'); return true") }
        fillPrisonerContactAgenciesDetailWith { text -> js.exec("tinymce.get('prisonerContactAgenciesDetail-tinymce').setContent('$text'); tinymce.get('prisonerContactAgenciesDetail-tinymce').fire('blur'); return true") }
    }
}
