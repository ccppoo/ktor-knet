package com.example.routes

import io.ktor.server.application.call
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.request.*
import io.ktor.http.*

import com.example.controller.MeidaController
import com.example.model.http.Formdata
import com.example.model.http.Multipart
import com.example.model.request.SampleJsonFormat
import com.example.model.request.MediaUploadFormData

fun Route.mediaApi(mediaController: MeidaController = MeidaController()){

    post("/json"){
        val jsonData = kotlin.runCatching { call.receive<SampleJsonFormat>() }.getOrElse {
            SampleJsonFormat(temp="Not in format!")
        }
        mediaController.SampleHandleJson(jsonData)
        call.response.status(HttpStatusCode.fromValue(202))
        call.respond(message = jsonData.temp)
    }

    post("/multipart"){
        val multipart = call.receiveMultipart()
        val tlqkf = MediaUploadFormData.from(multipart, Multipart.SampleFormFormat)
        mediaController.SampleHandleMultiFormData(tlqkf)

        call.response.status(HttpStatusCode.fromValue(202))
        call.respond(message = tlqkf as HashMap)
    }

    post("/formdata"){
        val formdata = call.receiveParameters()
        val tlqkfzz = MediaUploadFormData.from(formdata, Formdata.SampleFormdata)
        mediaController.SampleHandleFormData(tlqkfzz)

        call.response.status(HttpStatusCode.fromValue(202))
        call.respond(message = tlqkfzz as HashMap)
    }

    post("/binary"){
        // TODO : implement
        val streamData = call.receiveStream().use { stream ->
            Unit
        }
    }
}