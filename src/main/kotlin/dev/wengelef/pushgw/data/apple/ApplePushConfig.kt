package dev.wengelef.pushgw.data.apple

import dev.wengelef.pushgw.data.Notification
import kotlinx.serialization.Serializable

@Serializable
data class ApplePushConfig(
    val headers: Map<String, String>,
    val payload: Notification,
    val fcm_options: AppleFcmOptions
)

@Serializable
data class AppleFcmOptions(
    val analytics_label: String,
    val image: String
)
