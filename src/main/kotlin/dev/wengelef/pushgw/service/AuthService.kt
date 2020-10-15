package dev.wengelef.pushgw.service

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential
import java.io.FileInputStream

fun authenticator(): String {
    val credential = GoogleCredential
        .fromStream(FileInputStream("firebase-cloud-messenger.json"))
        .createScoped(listOf(
            "https://www.googleapis.com/auth/firebase.messaging",
            "https://www.googleapis.com/auth/cloud-platform")
        )

    credential.refreshToken()
    return credential.accessToken
}
