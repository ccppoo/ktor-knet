package com.example.model.response

interface Response {
    val status: State
    val message: String
}

/**
 * HTTP Response Status. Used for evaluation of [HttpResponse] type.
 */
enum class State {
    SUCCESS, NOT_FOUND, FAILED, UNAUTHORIZED

}