FROM maven:3.9.9-amazoncorretto-23 AS build
WORKDIR /app
COPY pom.xml ./
RUN mvn dependency:go-offline -B
COPY . .
RUN mvn clean package -DskipTests

FROM amazoncorretto:23-alpine
ARG AppVersion=1.0.0
ENV APP_VERSION=$AppVersion
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]