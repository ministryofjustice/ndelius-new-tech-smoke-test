package uk.gov.justice.digital.hmpps.delius.dataload

import static com.mongodb.client.model.Filters.eq

class AnalyticsDataLoader {
    static def clearSearchRequests() {
        println 'Clearing all search-request analytics events'
        def deleteResult = MongoDbHelper.db().getCollection('events').deleteMany(eq("type", "search-request"))
        println "Search request cleared. Rows deleted ${deleteResult.deletedCount}"
    }

}
