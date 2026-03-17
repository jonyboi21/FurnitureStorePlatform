# syntax=docker/dockerfile:1

FROM --platform=$BUILDPLATFORM maven:3.9-eclipse-temurin-21 AS build

WORKDIR /build

COPY pom.xml .
RUN mvn -q -e -B dependency:go-offline

COPY src ./src

RUN mvn package -DskipTests


FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

COPY --from=build /build/target/*.jar app.jar

EXPOSE 8000

ENTRYPOINT ["java","-jar","/app/app.jar"]