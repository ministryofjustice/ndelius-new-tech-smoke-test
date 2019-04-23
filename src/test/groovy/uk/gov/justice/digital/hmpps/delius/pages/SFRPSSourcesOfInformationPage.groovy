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
        sentencingGuidelinesInformationSourceCheckBox { $("#sentencingGuidelinesInformationSource") }
        otherInformationSourceCheckBox { $("#otherInformationSource") }
        fillOtherInformationDetailsWith { text -> $("#otherInformationDetails").value(text) }
    }
}
