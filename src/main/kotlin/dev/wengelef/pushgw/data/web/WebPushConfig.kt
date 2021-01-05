package dev.wengelef.pushgw.data.web

import dev.wengelef.pushgw.data.Notification
import dev.wengelef.pushgw.data.request.Data
import kotlinx.serialization.Serializable

@Serializable
data class WebPushConfig(
    val headers: Map<String, String>,
    val data: Data,
    val notification: Notification,
    val fcm_options: WebPushFcmOptions
)

@Serializable
data class WebPushFcmOptions(
    val link: String,
    val analytics_label: String
)
