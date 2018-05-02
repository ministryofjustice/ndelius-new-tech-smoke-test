package uk.gov.justice.digital.hmpps.delius.dataload

import org.bson.Document

import static com.mongodb.client.model.Filters.and
import static com.mongodb.client.model.Filters.eq
import static com.mongodb.client.model.Filters.exists
import static com.mongodb.client.model.Filters.ne


class FeedbackDataLoader {
    static def clearNationalSearchFeedback() {
        println 'Clearing all search feedback'
        def deleteResult = MongoDbHelper.db().getCollection('events').deleteMany(eq("type", "search-feedback"))
        println "Search Feedback cleared. Rows deleted ${deleteResult.deletedCount}"
    }

    static def clearSfpsrFeedback() {
        println 'Clearing all Short Format Pre-Sentence Report feedback'
        def deleteResult = MongoDbHelper.db().getCollection('events').deleteMany(and(exists("type", false), ne("feedback", null)))
        println "Report Feedback cleared. Rows deleted ${deleteResult.deletedCount}"
    }

    static def seedLegacySfpsrFeedbackDataFormat() {
        println 'Adding Short Format Pre-Sentence Report feedback in legacy format'

        MongoDbHelper.db().getCollection('events').insertOne(
            new Document([
                    "dateTime" : new Date(),
                    "feedback" : "A legacy feedback comment",
                    "pageNumber" : 99,
                    "sessionId" : "999c6b04-11a0-11e8-b642-0ed5f89f718b"
            ])
        )
        println 'Legacy report Feedback created'
    }


}
