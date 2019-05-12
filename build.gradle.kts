import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.21"
}

group = "dogbin.core"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(
        group = "org.jetbrains.xodus",
        name = "dnq",
        version = "1.3.440"
    )
    implementation(
        group = "at.favre.lib",
        name = "bcrypt",
        version = "0.8.0"
    )
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}