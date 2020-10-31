package dev.wengelef.pushgw.util

import arrow.core.Either
import arrow.core.computations.either
import dev.wengelef.pushgw.data.Message
import dev.wengelef.pushgw.data.config.FCMConfig
import dev.wengelef.pushgw.data.request.FcmError
import io.ktor.application.*

suspend inline fun <reified T : Any> withParameters(
    fcmConfig: FCMConfig,
    call: ApplicationCall,
    crossinline f: suspend (String, T) -> Either<FcmError, Message>): Either<FcmError, Message> = either {
    val (appId, body) = !getRequestParams<T>(fcmConfig, call)
    !f.invoke(appId, body)
}