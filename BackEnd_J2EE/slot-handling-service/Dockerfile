FROM openjdk:17
ADD ./target/slot-handling-service-0.0.1-SNAPSHOT.jar /usr/src/slot-handling-service-0.0.1-SNAPSHOT.jar
WORKDIR usr/src
ENTRYPOINT ["java","-jar", "slot-handling-service-0.0.1-SNAPSHOT.jar"]