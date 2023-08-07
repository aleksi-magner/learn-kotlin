import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

group = "ru.magner"
version = "1.0-SNAPSHOT"
description = "Проект для обучения Kotlin"

plugins {
    kotlin("jvm") version "1.9.0"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.diogonunes:JCDP:4.0.2")

    testImplementation(kotlin("test"))
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
}
