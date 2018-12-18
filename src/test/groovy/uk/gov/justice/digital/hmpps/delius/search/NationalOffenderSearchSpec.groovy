package uk.gov.justice.digital.hmpps.delius.search

import geb.driver.CachingDriverFactory
import geb.spock.GebReportingSpec
import spock.lang.Stepwise
import spock.lang.Unroll
import uk.gov.justice.digital.hmpps.delius.pages.IndexPage
import uk.gov.justice.digital.hmpps.delius.pages.NationalOffenderSearchPageFrame
import uk.gov.justice.digital.hmpps.delius.pages.NationalOffenderSearchPage

import static uk.gov.justice.digital.hmpps.delius.dataload.ESDataLoader.hasLoaded
import static uk.gov.justice.digital.hmpps.delius.dataload.ESDataLoader.replace

@Stepwise
class NationalOffenderSearchSpec extends GebReportingSpec {

    def setupSpec() {
        def offenders = [:]
        offenders[1] = offender( '/esdata/john-smith.json' )
        offenders[2] = offender( '/esdata/jane-smith.json' )
        offenders[3] = offender( '/esdata/sam-jones-deleted.json' )
        offenders[4] = offender( '/esdata/antonio-gramsci-n01.json' )
        offenders[5] = offender( '/esdata/antonio-gramsci-n02.json' )
        offenders[6] = offender( '/esdata/antonio-gramsci-n03.json' )
        offenders[7] = offender( '/esdata/anne-gramsci-n02.json' )
        offenders[8] = offender( '/esdata/antonio-gramsci-c20.json' )

        // we can no longer touch ES while it has been secured. So wait until Delius has moved
        // to AWS then we can find a secure way of accessing ES. In the meantime rely on the data
        // and index being correct
        // replace(offenders)
        to IndexPage
//        waitFor(10) {
//            hasLoaded(offenders)
//        }
    }

    def setup() {
        resetBrowser()
        CachingDriverFactory.clearCacheAndQuitDriver()
    }
    def 'Offender national Search presents search box'() {

        given: 'I am on the search page'
        to NationalOffenderSearchPageFrame

        when: 'I view the page'
        newTechFrame.isDisplayed()

        then: 'I see a search terms field'
        withFrame(newTechFrame, NationalOffenderSearchPage) {
            searchTerms.isDisplayed()
        }
    }

    def 'Searching for unique record returns one result'() {
        given: 'I am on the search page'
        to NationalOffenderSearchPageFrame

        when: 'I search for a specific CRN'
        withFrame(newTechFrame, NationalOffenderSearchPage) {
            enterSearchTerms('X00001')
        }

        then: 'I see a single offender record'
        withFrame(newTechFrame, NationalOffenderSearchPage) {
            waitFor {resultCount == 1}
            offenders[0].contains('X00001')
        }
    }

    def 'Searching for non-unique records returns multiple results'() {
        given: 'I am on the search page'
        to NationalOffenderSearchPageFrame

        when: 'I search for a matching surname'
        withFrame(newTechFrame, NationalOffenderSearchPage) {
            enterSearchTerms('smith')
        }

        then: 'I see multiple offender records'
        withFrame(newTechFrame, NationalOffenderSearchPage) {
            waitFor {resultCount == 2}
            offenders[0].contains('Smith')
            offenders[1].contains('Smith')
        }
    }

    def 'Searching for non-unique records with partial matches returns multiple results ordered'() {
        given: 'I am on the search page'
        to NationalOffenderSearchPageFrame

        and: 'Match any term is selected'
        withFrame(newTechFrame, NationalOffenderSearchPage) {
            enterSearchTerms('john smith')
            waitFor {resultCount > 0}
            deselectMatchAllTerms()
        }


        when: 'I search for a matching surname and firstname'
        withFrame(newTechFrame, NationalOffenderSearchPage) {
            enterSearchTerms('john smith')
        }

        then: 'I see multiple offender records'
        withFrame(newTechFrame, NationalOffenderSearchPage) {
            waitFor {resultCount == 2}
            offenders[0].contains('John')
            offenders[0].contains('Smith')
            offenders[1].contains('Jane')
            offenders[1].contains('Smith')
        }
    }

    def 'Search presents suggestions for near misses'() {
        given: 'I am on the search page'
        to NationalOffenderSearchPageFrame

        when: 'I search for `smth`'
        withFrame(newTechFrame, NationalOffenderSearchPage) {
            enterSearchTerms('smth')
        }

        then: 'I see a suggestions link for `smith`'
        withFrame(newTechFrame, NationalOffenderSearchPage) {
            waitFor{suggestionsFor('smith')}
        }
    }

    @Unroll('#searchTerm should find offender with PNC #displayPnc')
    def 'Searching for a record by PNC returns one result'(searchTerm, displayPnc) {
        given: 'I am on the search page'
        to NationalOffenderSearchPageFrame

        when: 'I search for a specific PNC'
        withFrame(newTechFrame, NationalOffenderSearchPage) {
            enterSearchTerms(searchTerm)
        }

        then: 'I see an offender record and the full PNC is displayed'
        withFrame(newTechFrame, NationalOffenderSearchPage) {
            waitFor {offenders[0].contains(displayPnc)}
            resultCount == 1
        }

        where:
        searchTerm      | displayPnc
        '2018/0123456X' | '2018/0123456X'
        '18/123456x'    | '2018/0123456X'
    }

    @Unroll('#searchTerm should find offender with CRO #displayCro')
    def 'Searching for a record by CRO returns one result'(searchTerm, displayCro) {
        given: 'I am on the search page'
        to NationalOffenderSearchPageFrame

        when: 'I search for a specific CRO'
        withFrame(newTechFrame, NationalOffenderSearchPage) {
            enterSearchTerms(searchTerm)
        }

        then: 'I see an offender record and the full CRO is displayed'
        withFrame(newTechFrame, NationalOffenderSearchPage) {
            waitFor {offenders[0].contains(displayCro)}
            resultCount == 1
        }

        where:
        searchTerm     | displayCro
        'SF80/655108T' | 'SF80/655108T'
        'sf80/655108t' | 'SF80/655108T'
    }

    def 'Searching for a record by DoB returns one result'() {
        given: 'I am on the search page'
        to NationalOffenderSearchPageFrame

        when: 'I search for a specific DoB'
        withFrame(newTechFrame, NationalOffenderSearchPage) {
            enterSearchTerms('1978/1/6')
        }

        then: 'I see a single offender record'
        withFrame(newTechFrame, NationalOffenderSearchPage) {
            waitFor {offenders[0].contains('X00001')}
            resultCount == 1
            offenders[0].contains('06/01/1978')
        }
    }

    def 'Searching for a soft deleted record returns no results'() {
        given: 'I am on the search page'
        to NationalOffenderSearchPageFrame

        when: 'I search for a deleted offender by CRN'
        withFrame(newTechFrame, NationalOffenderSearchPage) {
            enterSearchTerms('X00066')
        }

        then: 'I see no offender records'
        withFrame(newTechFrame, NationalOffenderSearchPage) {
            waitFor {resultCount == 0}
        }
    }

    def 'Searching by single letter returns offenders with that first name initial'() {
        given: 'I am on the search page'
        to NationalOffenderSearchPageFrame

        and: 'Match any term is selected'
        withFrame(newTechFrame, NationalOffenderSearchPage) {
            enterSearchTerms('john smith')
            waitFor {resultCount > 0}
            deselectMatchAllTerms()
        }

        when: 'I search for a first name initial'
        withFrame(newTechFrame, NationalOffenderSearchPage) {
            enterSearchTerms('J')
        }

        then: 'I see offender records that have that first name initial'
        withFrame(newTechFrame, NationalOffenderSearchPage) {
            // order not known so try either john or jane
            waitFor {resultCount > 1}
            waitFor {offenders[0].contains('Jane') || offenders[0].contains('John')}
            offenders[1].contains('John') || offenders[1].contains('Jane')
            offenders[0].contains('X00001') || offenders[0].contains('X00002')
            offenders[1].contains('X00002') || offenders[1].contains('X00001')
        }
    }

    def 'Previous search terms are saved so previous search is visible after returning to back the search page'() {
        given: 'I am on the search page'
        to NationalOffenderSearchPageFrame

        when: 'I search for an offender'
        withFrame(newTechFrame, NationalOffenderSearchPage) {
            enterSearchTerms('smith')
            waitFor {resultCount == 2}
        }
        and: 'Navigate back to search page'
        to NationalOffenderSearchPageFrame

        then: 'I see offender records from previous search'
        withFrame(newTechFrame, NationalOffenderSearchPage) {
            waitFor {resultCount == 2}
            offenders[0].contains('Smith')
            offenders[1].contains('Smith')
        }
    }

    def 'Searching with no filters selected finds all matches regardless of provider'() {
        given: 'I am on the search page'
        to NationalOffenderSearchPageFrame

        when: 'I search for a matching surname'
        withFrame(newTechFrame, NationalOffenderSearchPage) {
            enterSearchTerms('gramsci')
        }

        then: 'I see multiple offender records'
        withFrame(newTechFrame, NationalOffenderSearchPage) {
            waitFor {resultCount == 5}
        }
    }


    def 'Filters will be displayed with my providers and other providers'() {
        given: 'I am on the search page'
        to NationalOffenderSearchPageFrame

        when: 'I search for a matching surname'
        withFrame(newTechFrame, NationalOffenderSearchPage) {
            enterSearchTerms('gramsci')
        }

        then: 'I should see the my providers filter'
        withFrame(newTechFrame, NationalOffenderSearchPage) {
            waitFor({myProvidersFilter.isDisplayed()})
        }
        and: 'I should see the other providers filter'
        withFrame(newTechFrame, NationalOffenderSearchPage) {
            waitFor({otherProvidersFilter.isDisplayed()})
        }

    }
    def 'Searching with filters selected finds only those matching the active provider'() {
        given: 'I am on the search page'
        to NationalOffenderSearchPageFrame

        when: 'I search for a matching surname'
        withFrame(newTechFrame, NationalOffenderSearchPage) {
            enterSearchTerms('gramsci')
            waitFor {resultCount == 5}
        }
        and: 'I select an area filter'
        withFrame(newTechFrame, NationalOffenderSearchPage) {
            selectProviderFilter('N02', 'N01')
        }

        then: 'I see filtered multiple offender records'
        withFrame(newTechFrame, NationalOffenderSearchPage) {
            waitFor {resultCount == 3}
        }
    }

    def 'Searching with multiple basic terms and `Match all terms` set to Yes returns one result'() {
        given: 'I am on the search page'
        to NationalOffenderSearchPageFrame

        and: 'Match any term is initially selected'
        withFrame(newTechFrame, NationalOffenderSearchPage) {
            enterSearchTerms('john smith')
            waitFor {resultCount > 0}
            deselectMatchAllTerms()
        }

        when: 'I search using multiple terms that produce multiple results'
        withFrame(newTechFrame, NationalOffenderSearchPage) {
            enterSearchTerms('gramsci anne')
            waitFor{ matchAllTerms == "broad" }
            waitFor {resultCount == 5}
        }

        and: 'I select `Match all terms`'
        withFrame(newTechFrame, NationalOffenderSearchPage) {
            selectMatchAllTerms()
        }

        then: 'I see a single offender record'
        withFrame(newTechFrame, NationalOffenderSearchPage) {
            waitFor {resultCount == 1}
            offenders[0].contains('X00007')
        }
    }

    def 'Searching with surname and PNC and `Match all terms` set to Yes returns one result'() {
        given: 'I am on the search page'
        to NationalOffenderSearchPageFrame

        and: 'Match any term is initially selected'
        withFrame(newTechFrame, NationalOffenderSearchPage) {
            enterSearchTerms('john smith')
            waitFor {resultCount > 0}
            deselectMatchAllTerms()
        }

        when: 'I search using multiple terms that produce multiple results'
        withFrame(newTechFrame, NationalOffenderSearchPage) {
            enterSearchTerms('smith 2018/0123456X')
            waitFor{ matchAllTerms == "broad" }
            waitFor {resultCount == 2}
        }

        and: 'I select `Match all terms`'
        withFrame(newTechFrame, NationalOffenderSearchPage) {
            selectMatchAllTerms()
        }

        then: 'I see a single offender record'
        withFrame(newTechFrame, NationalOffenderSearchPage) {
            waitFor {resultCount == 1}
            offenders[0].contains('X00001')
        }
    }

    def 'Searching with surname and CRO and `Match all terms` set to Yes returns one result'() {
        given: 'I am on the search page'
        to NationalOffenderSearchPageFrame

        and: 'Match any term is initially selected'
        withFrame(newTechFrame, NationalOffenderSearchPage) {
            enterSearchTerms('john smith')
            waitFor {resultCount > 0}
            deselectMatchAllTerms()
        }

        when: 'I search using multiple terms that produce multiple results'
        withFrame(newTechFrame, NationalOffenderSearchPage) {
            enterSearchTerms('smith SF80/655108T')
            waitFor{ matchAllTerms == "broad" }
            waitFor {resultCount == 2}
        }

        and: 'I select `Match all terms`'
        withFrame(newTechFrame, NationalOffenderSearchPage) {
            selectMatchAllTerms()
        }

        then: 'I see a single offender record'
        withFrame(newTechFrame, NationalOffenderSearchPage) {
            waitFor {resultCount == 1}
            offenders[0].contains('X00001')
        }
    }

    def 'Searching with surname and DoB and `Match all terms` set to Yes returns one result'() {
        given: 'I am on the search page'
        to NationalOffenderSearchPageFrame

        and: 'Match any term is initially selected'
        withFrame(newTechFrame, NationalOffenderSearchPage) {
            enterSearchTerms('john smith')
            waitFor {resultCount > 0}
            deselectMatchAllTerms()
        }

        when: 'I search using multiple terms that produce multiple results'
        withFrame(newTechFrame, NationalOffenderSearchPage) {
            enterSearchTerms('smith 1978/1/6')
            waitFor{ matchAllTerms == "broad" }
            waitFor {resultCount == 2}
        }

        and: 'I select `Match all terms`'
        withFrame(newTechFrame, NationalOffenderSearchPage) {
            selectMatchAllTerms()
        }

        then: 'I see a single offender record'
        withFrame(newTechFrame, NationalOffenderSearchPage) {
            waitFor {resultCount == 1}
            offenders[0].contains('X00001')
        }
    }



    static def offender(String filename) {
        this.getClass().getResource(filename).text
    }
}
