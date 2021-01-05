package dev.wengelef.pushgw.data.request

import kotlinx.serialization.Serializable

@Serializable
data class Data(val data: Map<String, String>)