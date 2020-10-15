package dev.wengelef.pushgw.data.`in`

import kotlinx.serialization.Serializable

@Serializable
data class DataRequestBody(val data: Map<String, String>)