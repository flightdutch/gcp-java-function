FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Завантажуємо офіційний автономний інвокер від Google (standalone runner)
# Цей файл містить у собі весь потрібний рантайм і головний клас Runner
ADD https://repo1.maven.org/maven2/com/google/cloud/functions/java-function-invoker/1.4.1/java-function-invoker-1.4.1-shaded.jar invoker.jar

# Копіюємо ваш скомпільований Gradle-додаток (звичайний JAR, не shadow)
COPY build/libs/*.jar app.jar

EXPOSE 8080

# Запускаємо інвокер, який підхопить ваш клас через параметр --classpath
# Замініть com.example.App на ваш реальний клас функції (Entry Point)
CMD ["sh", "-c", "java -jar invoker.jar --classpath app.jar --target com.example.App --port ${PORT:-8080}"]
