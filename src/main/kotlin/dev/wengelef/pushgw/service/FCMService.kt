package dev.wengelef.pushgw.service

import arrow.core.Either
import dev.wengelef.pushgw.data.Message
import dev.wengelef.pushgw.data.Notification
import dev.wengelef.pushgw.data.PushBody
import dev.wengelef.pushgw.types.Authenticator
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*

fun sendDataPush(
    url: String,
    httpClient: HttpClient,
    authenticator: Authenticator
): suspend (data: Map<String, String>) -> Either<Throwable, Message> = { data ->

    Either.catch {
        httpClient.post {
            url(url)
            contentType(ContentType.Application.Json)
            header("Authorization", "Bearer ${authenticator()}")

            body = PushBody(message = Message(data = data, topic = "notifications"))
        }
    }
}

fun sendPush(
    url: String,
    httpClient: HttpClient,
    authenticator: Authenticator
): suspend (notification: Notification) -> Either<Throwable, Message> = { notification ->

    Either.catch {
        httpClient.post {
            url(url)
            contentType(ContentType.Application.Json)
            header("Authorization", "Bearer ${authenticator()}")

            body = PushBody(message = Message(notification = notification, topic = "notifications"))
        }
    }
}