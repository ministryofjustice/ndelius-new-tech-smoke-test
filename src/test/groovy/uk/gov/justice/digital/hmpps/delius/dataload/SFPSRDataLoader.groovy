package uk.gov.justice.digital.hmpps.delius.dataload

import groovy.json.JsonSlurper
import org.bson.Document


class SFPSRDataLoader {
    static def clear() {
        println 'Clearing all short format reports'
        MongoDbHelper.db().getCollection('shortFormatReports').drop()
        println 'Short format reports cleared'
    }

    static def reportAtPage(pageNumber) {
        def report = MongoDbHelper.db().getCollection('shortFormatReports').find().projection(new Document("originalData", 1)).first().get("originalData")
        def data = new JsonSlurper().parse(report.toString().toCharArray())
        println data
        data.values.pageNumber == pageNumber
    }
}
