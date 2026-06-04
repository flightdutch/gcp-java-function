plugins {
    // Підключаємо плагин для роботи з Java
    java
    // Плагин application дозволяє легко запускати додаток через Gradle
    application
}

repositories {
    // Репозиторій для завантаження залежностей (якщо вони знадобляться в майбутньому)
    mavenCentral()
}

dependencies {
    // Для простого прикладу сторонні бібліотеки не потрібні
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

jar {
    manifest {
        attributes["Main-Class"]: "com.example.App"
    }
}

tasks.withType<Jar> {
    manifest {
        attributes["Main-Class"] = "com.example.App"
    }
    // Цей рядок гарантує, що ВСІ скомпіловані класи потраплять в JAR
    from(sourceSets.main.get().output)
}
