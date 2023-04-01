FROM maven:3-openjdk-17 as build

WORKDIR /tmp
COPY pom.xml .
ADD src src
RUN mvn package spring-boot:repackage

FROM openjdk:17-jdk-slim

EXPOSE 8080
WORKDIR /usr/src/myapp
COPY --from=build /tmp/target/demo-0.0.1-SNAPSHOT.jar .
COPY src/main/resources/data/ src/main/resources/data/

CMD java -jar demo-0.0.1-SNAPSHOT.jar