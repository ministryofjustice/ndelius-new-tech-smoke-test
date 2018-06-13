package uk.gov.justice.digital.hmpps.delius.analytics

import geb.spock.GebReportingSpec
import groovy.json.JsonSlurper
import spock.lang.Stepwise
import uk.gov.justice.digital.hmpps.delius.pages.IndexPage
import uk.gov.justice.digital.hmpps.delius.pages.NationalOffenderSearchPage
import uk.gov.justice.digital.hmpps.delius.pages.NationalOffenderSearchPageFrame

import static uk.gov.justice.digital.hmpps.delius.Config.newTechBaseUrl
import static uk.gov.justice.digital.hmpps.delius.dataload.AnalyticsDataLoader.clearSearchRequests
import static uk.gov.justice.digital.hmpps.delius.dataload.ESDataLoader.hasLoaded
import static uk.gov.justice.digital.hmpps.delius.dataload.ESDataLoader.replace

@Stepwise
class NationalOffenderSearchAnalyticsSpec extends GebReportingSpec {

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
        replace(offenders)
        to IndexPage
        waitFor(10) {
            hasLoaded(offenders)
        }
        setup()
        clearSearchRequests()
    }

    def clearAndWaitSearchRequests() {
        clearSearchRequests()
        waitFor {
            def analytics = analyticsFor("filterCounts")
            // no rows when property is null
            analytics.hasUsedMyProvidersFilterCount == null
        }
    }

    def setup() {
        // clear any previous searches stored in local storage
        to NationalOffenderSearchPageFrame
        withFrame(newTechFrame, NationalOffenderSearchPage) {
            enterSearchTerms('zzzzzzzz') // initial search to clear first use banner and allow filters to be deselected
            waitFor {resultCount == 0}
            enterSearchTerms('') // clear local storage
            deselectAllMyProvidersSelectedFilters()
            deselectAllOtherProvidersSelectedFilters()
        }
        to IndexPage
        clearAndWaitSearchRequests()
    }

    def 'Selecting no filter should increment hasNotUsedFilterCount count'() {
        given: 'I am on the search page'
        to NationalOffenderSearchPageFrame

        when: 'I search for a matching surname'
        withFrame(newTechFrame, NationalOffenderSearchPage) {
            enterSearchTerms('gramsci')
            waitFor {resultCount == 5}
        }

        then: 'No filter used analytic is incremented'
        waitFor {
            def analytics = analyticsFor("filterCounts")
            analytics.hasUsedMyProvidersFilterCount == 0
            analytics.hasUsedOtherProvidersFilterCount == 0
            analytics.hasUsedBothProvidersFilterCount == 0
            analytics.hasNotUsedFilterCount == 1
        }
    }

    def 'Selecting an area from my providers filter should increment hasUsedMyProvidersFilterCount count'() {
        given: 'I am on the search page'
        to NationalOffenderSearchPageFrame

        when: 'I search for a matching surname'
        withFrame(newTechFrame, NationalOffenderSearchPage) {
            enterSearchTerms('gramsci')
            waitFor {resultCount == 5}
        }
        and: 'I select an area from my filter'
        withFrame(newTechFrame, NationalOffenderSearchPage) {
            selectAnyInMyProviderFilter()
            waitFor {resultCount < 5}
        }

        then: 'My filter used analytic is incremented'
        waitFor {
            def analytics = analyticsFor("filterCounts")
            analytics.hasUsedMyProvidersFilterCount == 1
            analytics.hasUsedOtherProvidersFilterCount == 0
            analytics.hasUsedBothProvidersFilterCount == 0
            analytics.hasNotUsedFilterCount == 0
        }
    }

    def 'Selecting an area from other providers filter should increment hasUsedOtherProvidersFilterCount count'() {
        given: 'I am on the search page'
        to NationalOffenderSearchPageFrame

        when: 'I search for a matching surname'
        withFrame(newTechFrame, NationalOffenderSearchPage) {
            enterSearchTerms('gramsci')
            waitFor {resultCount == 5}
        }
        and: 'I select an area from other filter'
        withFrame(newTechFrame, NationalOffenderSearchPage) {
            selectAnyInOtherProviderFilter()
            waitFor {resultCount < 5}
        }

        then: 'Other filter used analytic is incremented'
        waitFor {
            def analytics = analyticsFor("filterCounts")
            analytics.hasUsedMyProvidersFilterCount == 0
            analytics.hasUsedOtherProvidersFilterCount == 1
            analytics.hasUsedBothProvidersFilterCount == 0
            analytics.hasNotUsedFilterCount == 0
        }
    }

    def 'Selecting an area from other providers and my filters should increment hasUsedBothProvidersFilterCount count'() {
        given: 'I am on the search page'
        to NationalOffenderSearchPageFrame

        when: 'I search for a matching surname'
        withFrame(newTechFrame, NationalOffenderSearchPage) {
            enterSearchTerms('gramsci')
            waitFor {resultCount == 5}
        }
        and: 'I select an area from other filter'
        withFrame(newTechFrame, NationalOffenderSearchPage) {
            selectAnyInOtherProviderFilter()
            waitFor {resultCount == 1}
        }
        and: 'I select an area from my filter'
        withFrame(newTechFrame, NationalOffenderSearchPage) {
            selectNorthEastInMyProviderFilter()
            waitFor {resultCount > 1}
        }

        then: 'Other filter used analytic is incremented'
        waitFor {
            def analytics = analyticsFor("filterCounts")
            analytics.hasUsedMyProvidersFilterCount == 0
            analytics.hasUsedOtherProvidersFilterCount == 0
            analytics.hasUsedBothProvidersFilterCount == 1
            analytics.hasNotUsedFilterCount == 0
        }
    }

    def 'Broad search type is recorded'() {
        given: 'I am on the search page'
        to NationalOffenderSearchPageFrame

        when: 'I search for a matching surname'
        withFrame(newTechFrame, NationalOffenderSearchPage) {
            enterSearchTerms('gramsci')
            waitFor {resultCount == 5}
        }

        then: 'search type counts increase by 1'
        waitFor {
            def analytics = analyticsFor("searchTypeCounts")
            analytics.broad == 1
        }
    }

    def 'Exact search type is recorded'() {
        given: 'I am on the search page'
        to NationalOffenderSearchPageFrame

        when: 'I search for a matching surname'
        withFrame(newTechFrame, NationalOffenderSearchPage) {
            enterSearchTerms('gramsci')
            waitFor {resultCount == 5}
        }

        and: 'I select the `Match all terms` radio button'
        withFrame(newTechFrame, NationalOffenderSearchPage) {
            selectMatchAllTerms()
            waitFor {resultCount == 5}
        }

        then: 'search type counts increase by 1'
        waitFor {
            def analytics = analyticsFor("searchTypeCounts")
            analytics.exact == 1
        }
    }


    static def offender(String filename) {
        this.getClass().getResource(filename).text
    }
    static def analyticsFor(String metric) {new JsonSlurper().parse(new URL(newTechBaseUrl() + "nationalSearch/analytics/${metric}"))}
}
