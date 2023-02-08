package com.example.controller

import java.io.File

import com.example.utils.T
import com.example.utils.R

class MeidaController {

    fun isFileValid(file : File) : Boolean {
        // if false, delete file
        return true
    }

    fun getNewURLPath(file:File) : String {

        // TODO : file naming logic
        val newName = generateFileName(file)
        val newFile = File(file.parentFile,newName)
        file.renameTo(newFile)
        return "img/${newName}"
    }
}

fun MeidaController.generateFileName(originFile : File) : String {
    val newName = StringBuilder()
        .append(originFile.nameWithoutExtension)
        .append(T.getDateTime(pattern = T.TimeFormat_4))
        .append(R.int(1000, 5000))
        .append(".${originFile.extension}")
    return newName.toString()
}