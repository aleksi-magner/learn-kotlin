import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

group = "ru.magner"
version = "1.0-SNAPSHOT"
description = "Проект для обучения Kotlin"

plugins {
    kotlin("jvm") version "1.9.10"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.diogonunes:JCDP:4.0.2")
    implementation("com.squareup.moshi:moshi-kotlin:1.15.0")

    implementation("ch.qos.logback:logback-classic:1.4.11")

    implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.4.1")

    testImplementation("org.jetbrains.kotlin:kotlin-test:1.8.20-RC")
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.0")
    testImplementation("io.mockk:mockk:1.13.5")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(19))
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "19"
}

tasks.register("helloGradle") {
    doLast {
        println("Hello, Gradle!")
    }
}

tasks.test {
    useJUnitPlatform()

    testLogging {
        events("passed", "skipped", "failed") // "passed", "skipped", "failed"
    }
}
