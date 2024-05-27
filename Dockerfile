FROM maven:3.9.6-sapmachine-17 as builder
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline

LABEL maintainer="Piotr Piwonski <ptrpiw.dev@gmail.com>"
LABEL version="1.0"
LABEL description="App to run on k8s with argocd support"

COPY src/ ./src/
RUN mvn package -DskipTests

FROM openjdk:17
COPY --from=builder /app/target/hello-world-service.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]