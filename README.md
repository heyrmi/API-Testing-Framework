[![Tests](https://github.com/heyrmi/API-Testing-Framework/actions/workflows/tests.yml/badge.svg?branch=master)](https://github.com/heyrmi/API-Testing-Framework/actions/workflows/tests.yml)
[![Docker Image](https://github.com/heyrmi/API-Testing-Framework/actions/workflows/builddockerimage.yml/badge.svg?branch=master)](https://github.com/heyrmi/API-Testing-Framework/actions/workflows/builddockerimage.yml)
[![Alerts](https://github.com/heyrmi/API-Testing-Framework/actions/workflows/alerts.yml/badge.svg?branch=master)](https://github.com/heyrmi/API-Testing-Framework/actions/workflows/alerts.yml)

# API-Testing-Framework

API Testing using Rest-Assured & Postman, Load Testing using Gatling

- RestAssured / Gatling / Postman
- TestNG
- Extent Reports
- Java Faker
- Github Actions

## Features:

- Slack integration: Get slack messages after each test/deployment
- Latest Exection Report: View latest exection HTML report hosted using Github Pages
- Docker Image: Automatic docker image build and push to docker hub

## How to run tests:

Open Terminal in API-Testing-Framework folder and run (for debug logs use: `mvn -e -X clean test`):

```
#API Tests (Rest Assured)
mvn clean test --no-transfer-progress

#Load Tests (Gatling)
mvn clean gatling:test
```

To run the Rest Assured API Tests in docker container use this image [DockerHub](https://hub.docker.com/r/heyrmi/apitestingframework).
Works for both `linux/amd64` and `linux/arm64`.

Open the terminal and make a seperate directory for test reports

```
mkdir TestReports && cd TestReports
docker run -v ${PWD}:/home/apiframework/Reports heyrmi/apitestingframework
```

## Reports:

Report will be automatically published here: [Latest-Execution-Report](https://heyrmi.github.io/API-Testing-Framework)
