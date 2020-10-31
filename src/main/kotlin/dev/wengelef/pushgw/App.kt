package dev.wengelef.pushgw

import dev.wengelef.pushgw.data.config.FCMConfig
import dev.wengelef.pushgw.data.request.DataRequestBody
import dev.wengelef.pushgw.data.request.NotificationRequestBody
import dev.wengelef.pushgw.service.authenticator
import dev.wengelef.pushgw.service.sendDataPush
import dev.wengelef.pushgw.service.sendPush
import dev.wengelef.pushgw.util.respondOk
import dev.wengelef.pushgw.util.respondWith
import dev.wengelef.pushgw.util.responseValues
import dev.wengelef.pushgw.util.withParameters
import io.ktor.application.*
import io.ktor.client.*
import io.ktor.client.engine.apache.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.features.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.serialization.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.io.File

fun Application.module() {

    install(ContentNegotiation) {
        json()
    }
    install(DefaultHeaders)
    install(CallLogging)

    val fcmConfig: FCMConfig = File("fcm_app_config.json").readText(Charsets.UTF_8).trim()
        .let { config -> Json.decodeFromString(config) }

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

    val sendDataPush = sendDataPush(
        httpClient,
        ::authenticator
    )

    val sendNotification = sendPush(
        httpClient,
        ::authenticator
    )

    install(Routing) {
        get("/hello") {
            call.respondText { "Hello" }
        }

        post("/push") {
            withParameters<NotificationRequestBody>(fcmConfig, call) { appId, body -> sendNotification(appId, body.notification) }
                .fold(
                    ifLeft = { fcmError -> call.respondWith(fcmError.responseValues()) },
                    ifRight = { call.respondOk() }
                )
        }

        post("/data") {
            withParameters<DataRequestBody>(fcmConfig, call) { appId, body -> sendDataPush(appId, body.data) }
                .fold(
                    ifLeft = { fcmError -> call.respondWith(fcmError.responseValues()) },
                    ifRight = { call.respondOk() }
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
