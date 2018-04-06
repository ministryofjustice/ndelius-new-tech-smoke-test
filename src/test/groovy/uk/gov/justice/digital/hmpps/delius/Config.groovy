package uk.gov.justice.digital.hmpps.delius


class Config {
    static elasticSearchBaseUrl() {
        System.getenv('ELASTIC_SEARCH_URL') ?: "https://search-offender-smoke-test-766apkmmedr4zssxm3og4ssohq.eu-west-2.es.amazonaws.com/"
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
