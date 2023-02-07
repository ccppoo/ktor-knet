package com.example.routes

import com.example.model.URLResource.StaticImages
import io.ktor.http.*
import io.ktor.server.application.call
import io.ktor.server.auth.*
import io.ktor.server.http.content.*
import io.ktor.server.resources.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

import kotlinx.serialization.Serializable
import java.io.File

@Serializable
data class SampleDT(val indx : Int, val msg : String)

fun Route.StaticContentAPI(
    basePath : File
){


    authenticate{
        get<StaticImages.withName> { staticImages ->
            val file = File(basePath, "/uploads/${staticImages.name}")

            call.parameters.
            call.response.header(
                HttpHeaders.ContentDisposition,
                ContentDisposition.Attachment.withParameter(ContentDisposition.Parameters.FileName, "ktor_logo.png").toString()
            )
            call.response.status(HttpStatusCode.OK)
            call.respondFile(file)
        }

    }
}