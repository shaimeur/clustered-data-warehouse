# ---------- Stage 1: Build ----------
FROM maven:3.9.9-eclipse-temurin-17 AS builder


WORKDIR /app


COPY pom.xml .
RUN mvn dependency:go-offline -B


COPY src ./src


RUN mvn clean install -DskipTests

# ---------- Stage 2: Run ----------
FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app

COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]
