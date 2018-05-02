package uk.gov.justice.digital.hmpps.delius


class Config {
    static elasticSearchBaseUrl() {
        System.getenv('ELASTIC_SEARCH_URL') ?: "http://localhost:9200/"
    }
    static mongoConnectionUrl() {
        System.getenv('ANALYTICS_MONGO_CONNECTION') ?: 'mongodb://localhost'
    }
    static mongoDatabaseName() {
        System.getenv('ANALYTICS_MONGO_DATABASE') ?: 'analytics'
    }
    static newTechFeedbackBaseUrl() {
        System.getenv('NEW_TECH_FEEDBACK_URL') ?: 'http://feedback.user:changeit@localhost:9000/feedback'
    }
}
