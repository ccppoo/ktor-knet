package com.example.routes

import io.ktor.server.application.call
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.request.*
import io.ktor.http.*

import com.example.controller.MeidaController
import com.example.model.URLResource.StaticImages
import com.example.model.httpFormat.Formdata
import com.example.model.httpFormat.Multipart
import com.example.model.request.SampleJsonFormat
import com.example.model.request.MediaUploadFormData
import io.ktor.http.content.*
import io.ktor.server.auth.*
import io.ktor.server.resources.*
import kotlinx.serialization.Serializable
import java.io.File

@Serializable
data class SampleDT(val indx : Int, val msg : String)

@Serializable
data class ImageAccepted(val imageURL : String)

@Serializable
data class ImageNotAccepted(val reason : String)


fun Route.mediaApi(
    basePath : File,
    mediaController: MeidaController = MeidaController()
){
    val fileSaveHandler : suspend (PartData.FileItem) -> String = { fileItem ->
        val fileName = fileItem.originalFileName as String
        val fileBytes = fileItem.streamProvider().readBytes()
        val file = File(basePath, "uploads/$fileName")
        file.writeBytes(fileBytes)
        println("씨빨 !!! ${file.toString()}")
        file.toString()
    }

    authenticate {
        /**
         * 사진 불러올 때
         */
        get<StaticImages.withName> { staticImages ->
            val file = File(basePath, "/uploads/${staticImages.name}")

            val params : Parameters = call.parameters
            println(call.parameters["size"])

            call.response.header(
                HttpHeaders.ContentDisposition,
                ContentDisposition.Attachment.withParameter(ContentDisposition.Parameters.FileName, "ktor_logo.png").toString()
            )
            call.response.status(HttpStatusCode.OK)
            call.respondFile(file)
        }

        /**
         * 사진 업로드하고, 해당하는 이미지 URL 돌려주기
         */
        post("/img/post"){
            val multipart = call.receiveMultipart()
            val tlqkf = MediaUploadFormData.from(multipart, Multipart.UploadImageFromPost)
            println(tlqkf)
            val userSentFile = File(tlqkf["file"]!!)

            if (!mediaController.isFileValid(userSentFile)) {
                call.response.status(HttpStatusCode.NotAcceptable)
                call.respond(ImageNotAccepted(reason = "not allowed"))
                return@post
            }

            val newFileName = mediaController.getNewURLPath(userSentFile)

            call.response.status(HttpStatusCode.OK)
            call.respond(message = ImageAccepted(newFileName))
        }

    }

}