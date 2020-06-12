# ndelius-new-tech-smoke-test
End to end tests driving nDelius New Tech

These tests are run in CircleCI as part of an nDelius build.

## Running locally
Start the following components and services locally. Components should be configured to 
point at the local running services

1. Start Elasticsearch and Monogo Db with Docker-compose
1. Start the nDelius New Tech application
1. Start the nDelius wrapper

### Chromedriver

The smoke test uses a local copy of [chromedriver](https://chromedriver.chromium.org/downloads) which may need to be updated before running tests locally.

The local copy of chromedriver for mac64 can be found here:

`src/test/resources/webdriver/mac64`

### Running the tests

Once you have all services 

`./gradlew clean test` 

Reports can be found here: `build/reports/tests/test/index.html`
