package com.example.routes

import io.ktor.server.application.call
import io.ktor.server.auth.*
import io.ktor.client.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.util.*
import io.ktor.http.*

import com.example.controller.PingController
import com.example.model.request.PingRequest
import com.example.model.response.generateHttpResponse
import io.ktor.server.request.*

fun Route.pingApi(pingController: PingController = PingController()){

    get("/ping"){
        val pingRequest = kotlin.runCatching { call.receive<PingRequest>() }.getOrElse {
            PingRequest(ping = "asdasd")
        }
        val pongResponse = pingController.returnPong()
        val response = generateHttpResponse(pongResponse)

        call.response.status(response.code)
        call.respond(message = response.body)
    }

}