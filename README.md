# rfid-clone-spy
POC for system that will detect use of cloned RFID cards and notify about the event in real-time.

## Demo: ##
https://rfidclonespy.cfapps.io

## Prerequisits 
* Java 8
* Maven 

## Build

mvn clean install

### Builing a module

mvn clean install -pl :modulename

### Skiping tests

mvn clean install -DskipTests=true

### Runing tests

mvn test -pl :modulename

## Deployment

There is a continous deployment setup for PCF triggered with each master branch commit.
Application can be depoyed locally by startig the RestapiApplication in IntelliJ or by running:
java -jar restapi\target\restapi-0.0.1-SNAPSHOT-exec.jar




