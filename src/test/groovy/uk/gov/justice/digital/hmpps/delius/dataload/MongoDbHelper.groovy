package uk.gov.justice.digital.hmpps.delius.dataload

import com.mongodb.MongoClient
import com.mongodb.MongoClientURI


class MongoDbHelper {
    static def db() {
        MongoClient client = new MongoClient(new MongoClientURI(System.getenv('ANALYTICS_MONGO_CONNECTION') ?: 'mongodb://localhost'))
        client.getDatabase(System.getenv('ANALYTICS_MONGO_DATABASE') ?: 'analytics')
    }
}
