package com.example.controller

import com.example.model.response.PingResponse


class PingController {

    fun returnPong() : PingResponse = PingResponse.pong()

}