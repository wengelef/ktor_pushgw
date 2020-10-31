package dev.wengelef.pushgw.data.request

import kotlinx.serialization.Serializable

@Serializable
data class DataRequestBody(val data: Map<String, String>)