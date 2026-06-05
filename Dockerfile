FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# FIXED URL: Using the correct '-shaded' suffix for the standalone runner
ADD https://repo1.maven.org/maven2/com/google/cloud/functions/java-function-invoker/1.4.1/java-function-invoker-1.4.1-shaded.jar invoker.jar

# Copy your compiled application JAR file
COPY build/libs/*.jar app.jar

EXPOSE 8080

# Run the invoker, pointing to your app.jar and using the injected $PORT
CMD ["sh", "-c", "java -jar invoker.jar --classpath app.jar --target com.example.App --port ${PORT:-8080}"]
