package com.example.model.httpFormat

enum class FormDataType {
    // TODO : add types like Email-like, phone number-like, ect
    STRING, INT, FLOAT
}
object Formdata {

    val SampleFormdata = mapOf(
        "id" to FormDataType.STRING,
        "password" to FormDataType.STRING
    );

}