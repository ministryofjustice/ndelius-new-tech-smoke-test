package uk.gov.justice.digital.hmpps.delius.pages

class SFRPSOffenceDetailsPage extends SFPSRBasePage {

    static at = { heading == "Offence details" }

    static content = {
        heading { $("h1").text() }
        fillMainOffenceWith { text -> js.exec("return tinymce.get('mainOffence').setContent('$text')") }
        fillOtherOffencesWith { text -> js.exec("return tinymce.get('otherOffences').setContent('$text')") }
        fillOffenceSummaryWith { text -> js.exec("return tinymce.get('offenceSummary').setContent('$text')") }
    }
}
