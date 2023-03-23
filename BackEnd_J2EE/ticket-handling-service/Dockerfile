FROM openjdk:17
ADD ./target/ticket-handling-service-0.0.1-SNAPSHOT.jar /usr/src/ticket-handling-service-0.0.1-SNAPSHOT.jar
WORKDIR /usr/src
ENTRYPOINT ["java","-jar", "ticket-handling-service-0.0.1-SNAPSHOT.jar"]