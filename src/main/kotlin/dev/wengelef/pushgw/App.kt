package dev.wengelef.pushgw

import arrow.core.Either
import arrow.core.flatMap
import dev.wengelef.pushgw.data.`in`.DataRequestBody
import dev.wengelef.pushgw.service.authenticator
import dev.wengelef.pushgw.service.sendDataPush
import io.ktor.application.*
import io.ktor.client.*
import io.ktor.client.engine.apache.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.features.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.serialization.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import java.io.File

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

    install(ContentNegotiation) {
        json()
    }
    install(DefaultHeaders)
    install(CallLogging)

    val sendDataPush = sendDataPush(
        "https://fcm.googleapis.com/v1/projects/$fcmAppId/messages:send",
        httpClient,
        ::authenticator
    )

    install(Routing) {
        get("/hello") {
            call.respondText { "Hello" }
        }

        post("/data") {
            Either.catch { call.receive<DataRequestBody>() }
                .flatMap { body -> sendDataPush(body.data) }
                .fold(
                    ifLeft = { call.respondText { it.message ?: "Something went wrong" } },
                    ifRight = { messageResponse -> call.respondText { messageResponse.toString() } }
                )
        }
    }
}

fun main() {
    embeddedServer(
        Netty,
        8181,
        watchPaths = listOf("AppKt"),
        module = Application::module
    ).start(wait = true)
}
