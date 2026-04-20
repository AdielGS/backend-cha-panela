# Usa o Maven e o Java 21 para compilar o seu projeto
FROM maven:3.9.6-eclipse-temurin-21 AS build
COPY . .
RUN mvn clean package -DskipTests

# Usa o Java 21 para rodar o projeto pronto
FROM eclipse-temurin:21-jre
COPY --from=build target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]