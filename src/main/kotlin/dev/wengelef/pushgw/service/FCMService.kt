package dev.wengelef.pushgw.service

import arrow.core.Either
import dev.wengelef.pushgw.Message
import dev.wengelef.pushgw.MessageResponse
import dev.wengelef.pushgw.PushPostBody
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*

fun sendTestMessage(url: String, httpClient: HttpClient): suspend () -> Either<Throwable, MessageResponse> = {
    Either.catch {
        httpClient.post {
            url(url)
            contentType(ContentType.Application.Json)
            header("Authorization", "Bearer ${accessToken()}")

            val message = Message(
                name = "Message",
                data = mapOf("test" to "data"),
                topic = "notifications"
            )

            body = PushPostBody(false, message)
        }
    }
}