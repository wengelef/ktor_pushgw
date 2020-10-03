package dev.wengelef.pushgw

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    embeddedServer(Netty, 8181) {
        routing {
            routing {
                get("/") {
                    call.respondText("Example response", ContentType.Text.Html)
                }
            }
        }
    }.start(wait = true)
}
