package uk.gov.justice.digital.hmpps.delius.pages

class PP1CurrentRiskAssessmentPage extends PP1BasePage {
    static at = { heading == "Current risk assessment scores" }

    static content = {
        setRiskAssessmentRSRScore { text -> $("#riskAssessmentRSRScore").value(text) }
        setRiskAssessmentOGRS3ReoffendingProbability { text -> $("#riskAssessmentOGRS3ReoffendingProbability").value(text) }
        setRiskAssessmentOGPReoffendingProbability { text -> $("#riskAssessmentOGPReoffendingProbability").value(text) }
        setRiskAssessmentOVPReoffendingProbability { text -> $("#riskAssessmentOVPReoffendingProbability").value(text) }
        setRiskAssessmentMatrix2000AssessmentCompletedYes { $("#riskAssessmentMatrix2000AssessmentCompleted_yes").value("yes") }
        setRiskAssessmentMatrix2000ScoreLow { $("#riskAssessmentMatrix2000Score_low").value("low") }
        setRiskAssessmentSpousalAssaultAssessmentCompletedYes { $("#riskAssessmentSpousalAssaultAssessmentCompleted_yes").value("yes") }
        setRiskAssessmentSpousalAssaultScoreHigh { $("#riskAssessmentSpousalAssaultScore_high").value("high") }
    }
}
