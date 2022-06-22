# API-Testing-Framework

This is an Rest-API testing framework which is made from

- RestAssured
- TestNG
- Extent Reports
- Java Faker

---

- Please run this through TestNG XML, running individual tests from IDE will break the framework
- Since it depends on the TestNG listeners to start execution

---

## How to run:

Open Terminal in API-Testing-Framework and run (for detailed exection):

```
mvn -e -X clean test
```

To run the same in docker docker container use this image [DockerHub](https://hub.docker.com/r/heyrmi/apitestingframework) and open the terminal make a seperate folder for test reports

```
mkdir TestReports && cd TestReports
docker run -v ${PWD}:/home/apiframework/Reports heyrmi/apitestingframework
```
