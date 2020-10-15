group = "pushgw"
version = "${Versions.major}.${Versions.minor}.${Versions.patch}"


plugins {
    Dependencies.plugins
        .forEach { (plugin, pluginVersion) -> id(plugin) version pluginVersion }

    kotlin("plugin.serialization") version Versions.kotlin

    // Apply the application plugin to add support for building a CLI application.
    application
}

repositories {
    jcenter()
}


buildscript {
    repositories {
        mavenCentral()
    }

    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}")
        classpath("org.jetbrains.kotlin:kotlin-serialization:${Versions.kotlin}")
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))

    Dependencies.implementations
        .forEach { dependency -> implementation(dependency) }

    Dependencies.testImplementations
        .forEach { dependency -> testImplementation(dependency) }
}

application {
    mainClassName = "dev.wengelef.pushgw.AppKt"
}
