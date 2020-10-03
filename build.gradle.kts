group = "pushgw"
version = "${Versions.major}.${Versions.minor}.${Versions.patch}"


plugins {
    Dependencies.plugins
        .forEach { (plugin, pluginVersion) -> id(plugin) version pluginVersion }

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
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
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
