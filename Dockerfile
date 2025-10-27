
FROM eclipse-temurin:22-jdk-alpine

WORKDIR /app


COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src ./src


RUN chmod +x mvnw


RUN ./mvnw clean package -DskipTests


ENV PORT 8080
EXPOSE 8080


CMD ["java", "-jar", "target/cupcake-store-0.0.1-SNAPSHOT.jar"]
