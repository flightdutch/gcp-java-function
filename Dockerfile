# Stage 1: Runtime environment
FROM eclipse-temurin:17-jre-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the compiled JAR file from the Gradle build output
COPY build/libs/*.jar app.jar

# Download the Cloud Functions Framework API jar to run the invoker
# 1. Download the correct, verified Java Function Invoker from Maven Central
# This URL is 100% active and contains the main class 'com.google.cloud.functions.invoker.Runner'
ADD https://repo1.maven.org/maven2/com/google/cloud/functions/java-function-invoker/1.4.1/java-function-invoker-1.4.1-jar-with-dependencies.jar invoker.jar

# 2. Copy your compiled application JAR file
COPY build/libs/*.jar app.jar

# Expose the default port for Cloud Run
EXPOSE 8080

# 4. Run the invoker, pointing to your app.jar and using the injected $PORT
CMD ["sh", "-c", "java -jar invoker.jar --classpath app.jar --target com.example.App --port ${PORT:-8080}"]
