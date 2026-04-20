# Usa o Maven e o Java 21 para compilar o seu projeto
FROM maven:3.9.6-eclipse-temurin-21 AS build

# Copia tudo para dentro do servidor
WORKDIR /workspace
COPY . .

# ENTRA NA PASTA ONDE ESTÁ O SEU PROJETO DE VERDADE
WORKDIR /workspace/adieljulia

# Constrói o projeto
RUN mvn clean package -DskipTests

# Usa o Java 21 para rodar o projeto pronto
FROM eclipse-temurin:21-jre

# Pega o arquivo de dentro da pasta correta
COPY --from=build /workspace/adieljulia/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]