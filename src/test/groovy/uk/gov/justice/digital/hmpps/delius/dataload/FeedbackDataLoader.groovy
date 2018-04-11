package uk.gov.justice.digital.hmpps.delius.dataload

import static com.mongodb.client.model.Filters.and
import static com.mongodb.client.model.Filters.eq
import static com.mongodb.client.model.Filters.exists
import static com.mongodb.client.model.Filters.ne


class FeedbackDataLoader {
    static def clearNationalSearchFeedback() {
        println 'Clearing all search feedback'
        def deleteResult = MongoDbHelper.db().getCollection('events').deleteMany(eq("type", "search-feedback"))
        println 'Search Feedback cleared. Rows deleted ' + deleteResult.deletedCount
    }
    static def clearSfpsrFeedback() {
        println 'Clearing all Short Format Pre-Sentence Report feedback'
        def deleteResult = MongoDbHelper.db().getCollection('events').deleteMany(and(exists("type", false), ne("feedback", null)))
        println 'Report Feedback cleared. Rows deleted ' + deleteResult.deletedCount
    }
}
