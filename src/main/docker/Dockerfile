FROM maven:3.8.3-openjdk-11-slim AS build
WORKDIR /app
COPY . /app
RUN mvn install

FROM adoptopenjdk/openjdk11:alpine-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app/app.jar"]