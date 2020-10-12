package dev.wengelef.pushgw

import io.ktor.application.*
import io.ktor.client.*
import io.ktor.client.engine.apache.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.features.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.io.File
import java.io.FileInputStream

fun Application.module() {

    val fcmAppId = File("fcm_app_id").readText(Charsets.UTF_8).trim()

    val httpClient = HttpClient(Apache) {
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.ALL
        }
        install(JsonFeature) {
            val json = kotlinx.serialization.json.Json { ignoreUnknownKeys = true }
            serializer = KotlinxSerializer(json)
        }
    }

    install(DefaultHeaders)
    install(CallLogging)


    install(Routing) {
        get("/hello") {
            call.respondText { "Hello" }
        }

        val sendTestMessage = sendTestMessage(
            "https://fcm.googleapis.com/v1/projects/$fcmAppId/messages:send",
            httpClient
        )

        post("/test") {
            val result = sendTestMessage()
            call.respondText { result.toString() }
        }
    }
}

@Serializable
data class PushPostBody(
    @SerialName("validate_only") val validatOnly: Boolean = false,
    @SerialName("message") val message: Message
)

@Serializable
data class Message(
    @SerialName("name") val name: String,
    @SerialName("data") val data: Map<String, String>,
    @SerialName("topic") val topic: String
)

@Serializable data class MessageResponse(@SerialName("name") val name: String)

fun main() {
    embeddedServer(
        Netty,
        8181,
        watchPaths = listOf("AppKt"),
        module = Application::module
    ).start(wait = true)
}
