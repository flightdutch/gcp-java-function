# Stage 1: Runtime environment
FROM eclipse-temurin:17-jre-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the compiled JAR file from the Gradle build output
COPY build/libs/*.jar app.jar

# Download the Cloud Functions Framework API jar to run the invoker
ADD https://repo1.maven.org/maven2/com/google/cloud/functions/functions-framework-api/1.1.0/functions-framework-api-1.1.0.jar functions-framework-api.jar

# Expose the default port for Cloud Run
EXPOSE 8080

# Start the Google Functions Framework Invoker
# Added "--port" parameter that dynamically reads the $PORT variable from Cloud Run
CMD ["sh", "-c", "java -cp app.jar:functions-framework-api.jar com.google.cloud.functions.invoker.Runner --target com.example.App --port ${PORT:-8080}"]
