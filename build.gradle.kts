
repositories {
    // Репозиторій для завантаження залежностей (якщо вони знадобляться в майбутньому)
    mavenCentral()
}

plugins {
    // Підключаємо плагин для роботи з Java
    java
    // Плагин application дозволяє легко запускати додаток через Gradle
    application
}

dependencies {
    // Для простого прикладу сторонні бібліотеки не потрібні
    // Додаємо інструментарій Google Functions API - Офіційне API для написання функцій
    compileOnly("com.google.cloud.functions:functions-framework-api:1.1.0")

    // САМЕ ЦЕЙ пакет потрібен для локального запуску та запуску в Docker контейнерах
    implementation("com.google.cloud.functions:java-function-invoker:1.4.1")

    // Залишаємо JUnit для тестів, якщо є
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

testing {
    suites {
        val test by getting(JvmTestSuite::class) {
            useJUnitJupiter()
        }
    }
}

// Вказуємо Gradle, де знаходиться наш головний метод main()
application {
    mainClass.set("com.example.App")
}

// Налаштування версії Java для компиляції
java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

tasks.withType<Jar> {
    manifest {
        attributes["Main-Class"] = "com.example.App"
    }
    // Цей рядок гарантує, що ВСІ скомпіловані класи потраплять в JAR
    from(sourceSets.main.get().output)
}

