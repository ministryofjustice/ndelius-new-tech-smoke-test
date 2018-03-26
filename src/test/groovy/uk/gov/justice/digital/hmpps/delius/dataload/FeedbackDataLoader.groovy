package uk.gov.justice.digital.hmpps.delius.dataload

import static com.mongodb.client.model.Filters.eq


class FeedbackDataLoader {
    static def clear() {
        println 'Clearing all feedback'
        def deleteResult = MongoDbHelper.db().getCollection('events').deleteMany(eq("type", "search-feedback"))
        println 'Feedback cleared. Rows deleted ' + deleteResult.deletedCount
    }
}
