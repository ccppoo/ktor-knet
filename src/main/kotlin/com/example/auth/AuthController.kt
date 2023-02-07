package com.example.auth

import com.auth0.jwt.JWTVerifier
import com.example.model.config.SecurityConfig

// 향후 확장 가능성을 위해서 따로 AuthController를 만듦
class AuthController(config : SecurityConfig) {

    private val jwt: KNETwsJWTController

    init {
        jwt = KNETwsJWTController(config.JWTsecret)
    }

    val verifier : JWTVerifier
        get() = this.jwt.verifier
}