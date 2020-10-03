object Dependencies {
    val implementations = listOf(
        "org.jetbrains.kotlin:kotlin-stdlib-jdk8",
        "io.ktor:ktor-server-core:${Versions.ktor}",
        "io.ktor:ktor-server-netty:${Versions.ktor}",
        "ch.qos.logback:logback-classic:1.2.3"
    )

    val testImplementations = listOf(
        "org.jetbrains.kotlin:kotlin-test",
        "org.jetbrains.kotlin:kotlin-test-junit"
    )

    val plugins = mapOf("org.jetbrains.kotlin.jvm" to Versions.kotlin)
}