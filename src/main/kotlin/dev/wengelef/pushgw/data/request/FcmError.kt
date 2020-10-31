package dev.wengelef.pushgw.data.request

sealed class FcmError {
    object MissingRequestBody : FcmError()
    object MissingFcmConfig : FcmError()
    object FCMServiceError : FcmError()
}