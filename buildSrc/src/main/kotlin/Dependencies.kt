object Dependencies {
    val implementations = listOf(
        "org.jetbrains.kotlin:kotlin-stdlib-jdk8",
        "io.ktor:ktor-server-core:${Versions.ktor}",
        "io.ktor:ktor-server-netty:${Versions.ktor}",
        "io.ktor:ktor-client-core:${Versions.ktor}",
        "io.ktor:ktor-client-apache:${Versions.ktor}",
        "io.ktor:ktor-client-serialization:${Versions.ktor}",
        "io.ktor:ktor-client-logging-jvm:${Versions.ktor}",
        "io.ktor:ktor-serialization:${Versions.ktor}",
        "io.arrow-kt:arrow-core:${Versions.arrow}",
        "io.arrow-kt:arrow-syntax:${Versions.arrow}",
        "io.arrow-kt:arrow-fx:${Versions.arrow}",
        "ch.qos.logback:logback-classic:1.2.3",
        "com.google.firebase:firebase-admin:${Versions.firebase}"
    )

    val testImplementations = listOf(
        "org.jetbrains.kotlin:kotlin-test",
        "org.jetbrains.kotlin:kotlin-test-junit"
    )

    val plugins = mapOf("org.jetbrains.kotlin.jvm" to Versions.kotlin)
}