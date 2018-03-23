package uk.gov.justice.digital.hmpps.delius.dataload

import com.mongodb.MongoClient
import com.mongodb.MongoClientURI


class SFPSRDataLoader {
    static def clear() {
        println 'Clearing all short format reports'
        MongoClient client = new MongoClient(new MongoClientURI(System.getenv('ANALYTICS_MONGO_CONNECTION') ?: 'mongodb://localhost'))
        client.getDatabase(System.getenv('ANALYTICS_MONGO_DATABASE') ?: 'analytics').getCollection('shortFormatReports').drop()
        println 'Short format reports cleared'
    }
}
