package dev.wengelef.pushgw.data

import dev.wengelef.pushgw.data.web.WebPushConfig
import kotlinx.serialization.Serializable

@Serializable
data class Message(
    val name: String? = null,
    val data: Map<String, String>? = null,
    val notification: Notification? = null,
    val topic: String? = null,
    val webpush: WebPushConfig? = null
)

@Serializable
data class Notification(
    val title: String,
    val body: String,
    val image: String? = null
)
