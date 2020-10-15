package dev.wengelef.pushgw.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MessageResponse(@SerialName("name") val name: String)