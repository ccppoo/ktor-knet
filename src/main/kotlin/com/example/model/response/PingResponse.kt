package com.example.model.response

import kotlinx.serialization.Serializable

@Serializable
data class PingResponse (
    override val status: State,
    override val message: String,
    val hello: String
) : Response {

    companion object {
        fun pong() = PingResponse(State.SUCCESS, "pong", "world")
    }
}
