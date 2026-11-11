FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY target/selenium-login-test-1.0-SNAPSHOT.jar app.jar
COPY webpage/ ./webpage/
CMD ["java", "-cp", "app.jar", "org.testng.TestNG", "--help"]
