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

mvn clean install -DskipTest=true

### Runing tests

mvn test -pl :modulename



