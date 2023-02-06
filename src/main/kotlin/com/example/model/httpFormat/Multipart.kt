package com.example.model.httpFormat

enum class MultipartType {
    STRING, FILE, BINARY, BINARY_CHANNLE
}

object Multipart{
    val SampleFormFormat = mapOf(
        "file" to MultipartType.STRING,
        "name" to MultipartType.STRING
    )
}