package uk.gov.justice.digital.hmpps.delius.dataload

import com.mashape.unirest.http.Unirest
import groovy.json.JsonSlurper

import static uk.gov.justice.digital.hmpps.delius.Config.elasticSearchBaseUrl


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

    static def hasLoaded(Map<Long, String> offenders) {
        println 'Checking data is loaded'
        def jsonSlurper = new JsonSlurper()
        def details = jsonSlurper.parse(new URL(elasticSearchBaseUrl() + "offender/_count"))
        details.count == offenders.size()
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

}
