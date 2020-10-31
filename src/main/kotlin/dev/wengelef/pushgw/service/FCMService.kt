package dev.wengelef.pushgw.service

import arrow.core.Either
import dev.wengelef.pushgw.data.Message
import dev.wengelef.pushgw.data.Notification
import dev.wengelef.pushgw.data.PushBody
import dev.wengelef.pushgw.data.request.FcmError
import dev.wengelef.pushgw.types.Authenticator
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*

fun sendDataPush(
    httpClient: HttpClient,
    authenticator: Authenticator
): suspend (appId: String, data: Map<String, String>) -> Either<FcmError, Message> = { appId, data ->
    postFcmMessage(appId, httpClient, authenticator) {
        body = PushBody(message = Message(data = data, topic = "notifications"))
    }
}

fun sendPush(
    httpClient: HttpClient,
    authenticator: Authenticator
): suspend (appId: String, notification: Notification) -> Either<FcmError, Message> = { appId, notification ->
    postFcmMessage(appId, httpClient, authenticator) {
        body = PushBody(message = Message(notification = notification, topic = "notifications"))
    }
}

suspend fun postFcmMessage(
    appId: String,
    httpClient: HttpClient,
    authenticator: Authenticator,
    requestConfig: HttpRequestBuilder.() -> Unit,
): Either<FcmError, Message> {
    val url = "https://fcm.googleapis.com/v1/projects/$appId/messages:send"
    val result = Either.catch<Message> {
        httpClient.post {
            url(url)
            contentType(ContentType.Application.Json)
            header("Authorization", "Bearer ${authenticator()}")
            requestConfig()
        }
    }
    return result.mapLeft { FcmError.FCMServiceError }
}