
#FROM gradle AS build
#
##COPY --chown=gradle . /src
#
#WORKDIR /src
#
#RUN gradle build --stacktrace --continue

FROM openjdk:8-jre-alpine AS final
ADD build/libs/FastestProxy-1.0-SNAPSHOT.jar app.jar
#RUN sh -c 'touch /FastestProxy-1.0-SNAPSHOT.jar'

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
#"-Dspring.data.mongodb.uri=mongodb://springboot-mongo:27017/springmongo-demo"
EXPOSE 8080



