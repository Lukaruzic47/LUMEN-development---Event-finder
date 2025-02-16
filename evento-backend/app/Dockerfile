FROM eclipse-temurin:21-jdk AS build

WORKDIR /app

COPY mvnw* pom.xml ./
COPY .mvn .mvn

COPY src ./src

RUN chmod +x ./mvnw

RUN ./mvnw clean package -DskipTests

FROM eclipse-temurin:21-jre AS runtime

WORKDIR /app

COPY --from=build /app/target/app-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "app.jar"]
