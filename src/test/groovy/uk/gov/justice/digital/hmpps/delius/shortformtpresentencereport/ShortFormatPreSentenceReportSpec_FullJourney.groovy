package uk.gov.justice.digital.hmpps.delius.shortformtpresentencereport

import geb.spock.GebReportingSpec
import spock.lang.Stepwise
import uk.gov.justice.digital.hmpps.delius.dataload.SFPSRDataLoader
import uk.gov.justice.digital.hmpps.delius.pages.*

@Stepwise
class ShortFormatPreSentenceReportSpec_FullJourney extends GebReportingSpec {

    def setup() {
        SFPSRDataLoader.clear()
        to IndexPage
    }

    def 'Create report'() {

        given: 'I create a report'
        to ShortFormatPreSentenceReportPageFrame
        withFrame(newTechFrame, SFRPSWelcomePage) {
            startNowButton.click()
        }

        withFrame(newTechFrame, SFRPSOffenderDetailsPage) {
            headingDisplayed
            fillAddressWith("22 Acacia Avenue")
            fillPncWith("2018/1234567A")
            saveAndContinue.click()
        }

        withFrame(newTechFrame, SFRPSSentencingCourtDetailsPage) {
            headingDisplayed
            fillCourtWith("The court")
            fillLocalJusticeAreaWith("Cardiff")
            saveAndContinue.click()
        }

        withFrame(newTechFrame, SFRPSSourcesOfInformationPage) {
            headingDisplayed
            interviewInformationSourceCheckBox.click()
            serviceRecordsInformationSourceCheckBox.click()
            cpsSummaryInformationSourceCheckBox.click()
            oasysAssessmentsInformationSourceCheckBox.click()
            previousConvictionsInformationSourceCheckBox.click()
            victimStatementInformationSourceCheckBox.click()
            childrenServicesInformationSourceCheckBox.click()
            policeInformationSourceCheckBox.click()
            sentencingGuidelinesInformationSourceCheckBox.click()
            otherInformationSourceCheckBox.click()
            fillOtherInformationDetailsWith("Other information details")
            saveAndContinue.click()
        }

        withFrame(newTechFrame, SFRPSOffenceDetailsPage) {
            headingDisplayed
            fillMainOffenceWith("Main offence")
            fillOtherOffencesWith("Other offences")
            fillOffenceSummaryWith("Summary of offence")
            saveAndContinue.click()
        }

        withFrame(newTechFrame, SFRPSOffenceAnalysisPage) {
            headingDisplayed
            fillOffenceAnalysisWith("Offence analysis")
            fillPatternOfOffendingWith("Pattern of offending")
            saveAndContinue.click()
            waitFor(5)
        }

    }

    private void waitFor(int seconds) {
        Thread.sleep(seconds * 1000)
    }

}
