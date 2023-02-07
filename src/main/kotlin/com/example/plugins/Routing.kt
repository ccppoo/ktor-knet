package com.example.plugins

import com.example.basePath
import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.http.content.*
import io.ktor.server.application.*
import com.example.routes.*
import io.ktor.server.resources.*
import java.io.File

fun Application.configureRouting(
    basePath : File = this.basePath
) {
    install(Resources)
    routing {
        get("/") {
            call.respondText("Hello World!")
        }

        pingApi()
        mediaApi(basePath)
    }
}
