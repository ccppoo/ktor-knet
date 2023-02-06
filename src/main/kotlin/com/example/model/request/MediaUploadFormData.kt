package com.example.model.request

import io.ktor.http.*
import io.ktor.http.content.*
import java.io.File
import com.example.model.http.*



object MediaUploadFormData {

    suspend open fun handleMultipartItem(partData: PartData.FormItem) : String?{
        // override and make method to hanlde data
        // for example, if key name is wrong skip (return null)
        return partData.value
    }

    suspend open fun handleMultipartFile(partData: PartData.FileItem) : String?{
        // TODO : Fix behavior when file is uploaded (for exmaple, check extension or save or not)
        // TODO : make this able to override by user behavior
        // return `file path`(string) after saving file that client uploaded
        val fileName = partData.originalFileName as String
        val fileBytes = partData.streamProvider().readBytes()
        val filePathName = "uploads/$fileName"
        File(filePathName).writeBytes(fileBytes)
        return filePathName
    }

    suspend fun from(multiPartData : MultiPartData, mappedLike : Map<String, MultipartType>) : Map<String, String>{
        val tempMap = mutableMapOf<String,String>();

        multiPartData.forEachPart { part ->
            // return if key name doesn't match
            mappedLike[part.name] ?: return@forEachPart
            when (part){
                is PartData.FormItem -> {
                    val value : String = this.handleMultipartItem(part) ?: return@forEachPart
                    tempMap[part.name!!] = value
                }
                is PartData.FileItem -> {
                    val value : String = this.handleMultipartFile(part) ?: return@forEachPart
                    tempMap[part.name!!] = value
                }
                is PartData.BinaryItem -> {
                    Unit
                }
                is PartData.BinaryChannelItem -> {
                    Unit
                }
            }
        }
        return tempMap
    }

    fun from(formData : Parameters, mappedLike : Map<String, FormDataType>) : Map<String, Any>{
        val tempMap = mutableMapOf<String,Any>();

        mappedLike.forEach {
            val formdata : String = formData[it.key] ?: return@forEach
            when (it.value){
                // Check value type (String is default)
                FormDataType.STRING -> {
                    tempMap[it.key] = formdata
                }
                FormDataType.INT -> {
                    tempMap[it.key] = formdata.toIntOrNull() ?: return@forEach
                }
                FormDataType.FLOAT -> {
                    tempMap[it.key] = formdata.toFloatOrNull() ?: return@forEach
                }
            }
        }
        return tempMap
    }
}


