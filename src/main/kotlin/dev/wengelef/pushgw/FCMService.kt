package dev.wengelef.pushgw

import arrow.core.Either
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
                topic = "all"
            )

            body = PushPostBody(true, message)
        }
    }
}