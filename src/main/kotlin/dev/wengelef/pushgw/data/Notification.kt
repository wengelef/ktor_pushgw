package dev.wengelef.pushgw.data

import kotlinx.serialization.Serializable

@Serializable
data class Notification(
    val title: String,
    val body: String,
    val image: String
)