package com.example.model.request

import kotlinx.serialization.Serializable

@Serializable
data class PingRequest(val ping : String)