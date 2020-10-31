package dev.wengelef.pushgw.data.config

import kotlinx.serialization.Serializable

@Serializable
data class FCMConfig(
    val android: PlatformConfig? = null,
    val ios: PlatformConfig? = null
)

@Serializable
data class PlatformConfig(
    val test: String? = null,
    val production: String? = null
)

fun FCMConfig.availableIds(isTest: Boolean): Pair<String?, String?> = if (isTest) {
    android?.test to ios?.test
} else {
    android?.production to ios?.production
}