package dev.wengelef.pushgw.data

import kotlinx.serialization.Serializable

@Serializable
data class Message(
    val name: String? = null,
    val data: Map<String, String>? = null,
    val topic: String? = null
)