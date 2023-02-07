package com.example.controller

import java.io.File

class MeidaController {

    fun isFileValid(file : File) : Boolean {
        // if false, delete file
        return true
    }

    fun getNewURLPath(file:File) : String {

        // TODO : file naming logic
        val newName = "new_${file.name}"
        val newFile = File(file.parentFile,newName )

        file.renameTo(newFile)

        return newName
    }
}