package uk.gov.justice.digital.hmpps.delius.pages

class SFRPSSourcesOfInformationPage extends SFPSRBasePage {

    static content = {
        headingDisplayed { waitFor(5) { $("h1", text: "Sources of information") } }
        interviewInformationSourceCheckBox { $("#interviewInformationSource") }
        serviceRecordsInformationSourceCheckBox { $("#serviceRecordsInformationSource") }
        cpsSummaryInformationSourceCheckBox { $("#cpsSummaryInformationSource") }
        oasysAssessmentsInformationSourceCheckBox { $("#oasysAssessmentsInformationSource") }
        previousConvictionsInformationSourceCheckBox { $("#previousConvictionsInformationSource") }
        victimStatementInformationSourceCheckBox { $("#victimStatementInformationSource") }
        childrenServicesInformationSourceCheckBox { $("#childrenServicesInformationSource") }
        policeInformationSourceCheckBox { $("#policeInformationSource") }
        sentencingGuidelinesInformationSourceCheckBox { $("#sentencingGuidelinesInformationSource") }
        otherInformationSourceCheckBox { $("#otherInformationSource") }
        fillOtherInformationDetailsWith { text -> $("#otherInformationDetails").value(text) }
    }
}
