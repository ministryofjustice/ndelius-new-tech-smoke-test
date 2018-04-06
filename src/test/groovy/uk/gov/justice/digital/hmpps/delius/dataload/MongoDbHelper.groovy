package uk.gov.justice.digital.hmpps.delius.dataload

import com.mongodb.MongoClient
import com.mongodb.MongoClientURI

import static uk.gov.justice.digital.hmpps.delius.Config.mongoConnectionUrl
import static uk.gov.justice.digital.hmpps.delius.Config.mongoDatabaseName


class MongoDbHelper {
    static def db() {
        MongoClient client = new MongoClient(new MongoClientURI(mongoConnectionUrl()))
        client.getDatabase(mongoDatabaseName())
    }
}
