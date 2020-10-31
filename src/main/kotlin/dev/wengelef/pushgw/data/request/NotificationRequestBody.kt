package dev.wengelef.pushgw.data.request

import dev.wengelef.pushgw.data.Notification
import kotlinx.serialization.Serializable

@Serializable
data class NotificationRequestBody(val notification: Notification)