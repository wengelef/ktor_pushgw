package dev.wengelef.pushgw.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PushBody(
    @SerialName("validate_only") val validatOnly: Boolean = false,
    @SerialName("message") val message: Message,
)

