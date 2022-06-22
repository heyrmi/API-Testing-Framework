FROM maven:3.8.6-jdk-11

COPY src home/apiframework/src
COPY output home/apiframework/output
COPY Reports home/apiframework/Reports
COPY pom.xml home/apiframework/pom.xml
COPY testng.xml home/apiframework/testng.xml

WORKDIR home/apiframework/
ENTRYPOINT mvn -e -X clean test