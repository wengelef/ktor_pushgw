package dev.wengelef.pushgw.data

import kotlinx.serialization.Serializable

@Serializable
data class Message(val data: Map<String, String>, val topic: String)