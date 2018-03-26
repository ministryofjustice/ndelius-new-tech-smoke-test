package uk.gov.justice.digital.hmpps.delius.dataload


class SFPSRDataLoader {
    static def clear() {
        println 'Clearing all short format reports'
        MongoDbHelper.db().getCollection('shortFormatReports').drop()
        println 'Short format reports cleared'
    }
}
