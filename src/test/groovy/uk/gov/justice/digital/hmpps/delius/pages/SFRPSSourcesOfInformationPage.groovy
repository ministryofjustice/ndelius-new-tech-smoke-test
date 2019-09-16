package uk.gov.justice.digital.hmpps.delius.pages

class SFRPSSourcesOfInformationPage extends SFPSRBasePage {

    static at = {  heading == "Sources of information" }

    static content = {
        heading { $("h1").text() }
        interviewInformationSourceCheckBox { $("#interviewInformationSource") }
        serviceRecordsInformationSourceCheckBox { $("#serviceRecordsInformationSource") }
        cpsSummaryInformationSourceCheckBox { $("#cpsSummaryInformationSource") }
        oasysAssessmentsInformationSourceCheckBox { $("#oasysAssessmentsInformationSource") }
        previousConvictionsInformationSourceCheckBox { $("#previousConvictionsInformationSource") }
        victimStatementInformationSourceCheckBox { $("#victimStatementInformationSource") }
        childrenServicesInformationSourceCheckBox { $("#childrenServicesInformationSource") }
        policeInformationSourceCheckBox { $("#policeInformationSource") }
        domesticAbuseInformationSourceCheckBox { $("#domesticAbuseInformationSource") }
        equalityInformationFormInformationSourceCheckBox { $("#equalityInformationFormInformationSource") }
        sentencingGuidelinesInformationSourceCheckBox { $("#sentencingGuidelinesInformationSource") }
        otherInformationSourceCheckBox { $("#otherInformationSource") }
        fillOtherInformationDetailsWith { text -> js.exec("tinymce.get('otherInformationDetails-tinymce').setContent('$text'); tinymce.get('otherInformationDetails-tinymce').fire('blur'); return true") }
    }
}
