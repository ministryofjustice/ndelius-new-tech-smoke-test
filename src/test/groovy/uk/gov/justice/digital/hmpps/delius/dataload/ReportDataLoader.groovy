package uk.gov.justice.digital.hmpps.delius.dataload

import groovy.json.JsonSlurper
import org.bson.Document


class ReportDataLoader {
    static def clear() {
        println 'Clearing all reports'
        MongoDbHelper.db().getCollection('reports').drop()
        println 'All reports cleared'
    }

    static def reportAtPage(pageNumber) {
        def report = MongoDbHelper.db().getCollection('reports').find().projection(new Document("originalData", 1)).first().get("originalData")
        def data = new JsonSlurper().parse(report.toString().toCharArray())
        println data
        data.values.pageNumber == pageNumber
    }
}
