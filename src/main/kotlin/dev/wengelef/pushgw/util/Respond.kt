package dev.wengelef.pushgw.util

import arrow.core.Either
import arrow.core.computations.either
import dev.wengelef.pushgw.data.config.FCMConfig
import dev.wengelef.pushgw.data.config.availableIds
import dev.wengelef.pushgw.data.request.DataRequestBody
import dev.wengelef.pushgw.data.request.FcmError
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.util.*

fun FcmError.responseValues(): Pair<HttpStatusCode, String> = when (this) {
    FcmError.MissingRequestBody -> HttpStatusCode.BadRequest to "Missing request body"
    FcmError.MissingFcmConfig -> HttpStatusCode.BadRequest to "Missing fcm config"
    FcmError.FCMServiceError -> HttpStatusCode.InternalServerError to "Sending Push Failed"
}

suspend fun ApplicationCall.respondWith(values: Pair<HttpStatusCode, String>) {
    respond(values.first, DataRequestBody(mapOf("message" to values.second)))
}

suspend fun ApplicationCall.respondOk() = respondWith(HttpStatusCode.OK to "Success")

suspend inline fun <reified T : Any> getRequestParams(fcmConfig: FCMConfig, call: ApplicationCall): Either<FcmError, Pair<String, T>> = either {
    val isTest = call.request.queryParameters.flattenEntries().toMap()["test"]?.toBoolean() ?: false

    val fcmAppIds = fcmConfig.availableIds(isTest)

    val appId = !Either.catch { fcmAppIds.first!! }
        .mapLeft { FcmError.MissingFcmConfig }

    val body = !Either.catch { call.receive<T>() }
        .mapLeft { FcmError.MissingRequestBody }

    appId to body
}