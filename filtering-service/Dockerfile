FROM openjdk:8-jdk-alpine
LABEL maintainer="daniel.naves@outlook.com"
VOLUME /tmp
EXPOSE 8080
ARG JAR_FILE=target/filtering-service-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} filtering-service.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/filtering-service.jar"]
