package uk.gov.justice.digital.hmpps.delius.dataload

import com.mashape.unirest.http.Unirest


class ESDataLoader {
    static def replace(data) {
        clear()
        load(data)
    }
    static def clear() {
        destroyIndex()
        destroyPipeline()
        buildIndex()
        buildPipeline()
    }

    static def load(Map<Long, String> offenders) {
        println 'Loading data'
        offenders.each {key, offender -> loadOffender( key, offender)}
    }

    static def loadOffender(key, offender) {
        println 'Loading offender'
        def responseCode = Unirest.put(elasticSearchBaseUrl() + "offender/document/" + key + "?pipeline=pnc-pipeline")
                .header("Content-Type", "application/json")
                .body(offender)
                .asJson()
                .status

        println 'Offender loaded. Response is ' + responseCode

    }


    static private destroyIndex = {
        println 'Dropping offender index'

        def responseCode = Unirest.delete(elasticSearchBaseUrl() + "offender")
                .asJson()
                .status

        println 'Dropped offender index. Response is ' + responseCode

    }

    static private destroyPipeline = {
        println 'Dropping pipeline'

        def responseCode = Unirest.delete(elasticSearchBaseUrl() + "_ingest/pipeline/pnc-pipeline")
                .asJson()
                .status

        println 'Dropped pipeline Response is ' + responseCode

    }

    static private buildIndex = {
        println 'Building offender index'
        def responseCode = Unirest.put(elasticSearchBaseUrl() + "offender")
                .header("Content-Type", "application/json")
                .body(this.getClass().getResource( '/esdata/create-index.json' ).text)
                .asJson()
                .status

        println 'Create offender index. Response is ' + responseCode

    }

    static private buildPipeline = {
        println 'Building pipeline'
        def responseCode = Unirest.put(elasticSearchBaseUrl() + "_ingest/pipeline/pnc-pipeline")
                .header("Content-Type", "application/json")
                .body(this.getClass().getResource( '/esdata/create-pipeline.json' ).text)
                .asJson()
                .status

        println 'Create pipeline. Response is ' + responseCode

    }

    static private elasticSearchBaseUrl() {
        return System.getenv('ELASTIC_SEARCH_URL') ?: "https://search-offender-smoke-test-766apkmmedr4zssxm3og4ssohq.eu-west-2.es.amazonaws.com/"
    }
}