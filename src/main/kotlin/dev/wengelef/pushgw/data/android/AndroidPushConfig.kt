package dev.wengelef.pushgw.data.android

import dev.wengelef.pushgw.data.request.Data
import kotlinx.serialization.Serializable

@Serializable
data class AndroidPushConfig(
    val collapse_key: String,
    val priority: AndroidMessagePriority,
    val ttl: String,
    val restricted_package_name: String,
    val data: Data,
    val notification: AndroidNotification,
    val fcm_options: AndroidFcmOptions,
    val direct_boot_ok: Boolean = true
)

@Serializable
data class AndroidFcmOptions(
    val analytics_label: String
)

@Serializable
data class AndroidNotification(
    val title: String? = null,
    val body: String? = null,
    val icon: String? = null,
    val color: String? = null,
    val sound: String = "default",
    val tag: String? = null,
    val click_action: String? = null,
    val body_loc_key: String? = null,
    val body_loc_args: List<String>? = null,
    val title_loc_key: String? = null,
    val title_loc_args: List<String>? = null,
    val channel_id: String? = null,
    val ticker: String? = null,
    val sticky: Boolean = false,
    val event_time: String? = null,
    val local_only: Boolean? = null,
    val notification_priority: NotificationPriority = NotificationPriority.PRIORITY_DEFAULT,
    val default_sound: Boolean = true,
    val default_vibrate_timings: Boolean = true,
    val vibrate_timings: List<String>? = null,
    val visibility: Visibility = Visibility.VISIBILITY_UNSPECIFIED,
    val notification_count: Int = 0,
    val image: String? = null
)

enum class Visibility {
    VISIBILITY_UNSPECIFIED, PRIVATE, PUBLIC, SECRET
}

enum class NotificationPriority {
    PRIORITY_UNSPECIFIED, PRIORITY_MIN, PRIORITY_LOW, PRIORITY_DEFAULT, PRIORITY_HIGH, PRIORITY_MAX
}

enum class AndroidMessagePriority { NORMAL, HIGH }
