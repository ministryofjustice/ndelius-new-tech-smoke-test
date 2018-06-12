# ndelius-new-tech-smoke-test
End to end tests driving nDelius New Tech

These tests are run in CircleCI as part of an nDelius build.

## Running locally
Start the following components and services locally. Components should be configured to 
point at the local running services

1. Start Monogo Db
1. Start Elasticsearch 
1. Start the nDelius New Tech application
1. Start the nDelius wrapper

`./gradlew clean test` 

Reports can be found here: `build/reports/tests/test/index.html`
