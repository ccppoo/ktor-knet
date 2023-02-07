package com.example.auth

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
//import javax.inject.Inject

interface JWTController {

    val verifier: JWTVerifier

//    fun sign(data: String): Unit {
//        단순히 이미지를 업로드, 다운로드 해주는 역할을 함(로그인 관여 X, 회원 전용 컨텐츠라고 이해하면 편함)
//        JWT 토큰이 유효한지만 알고 있으면 된다
//    }
}

class KNETwsJWTController(secret: String) : JWTController {
    private val algorithm = Algorithm.HMAC256(secret)
    override val verifier: JWTVerifier = JWT
        .require(algorithm)
        .withIssuer(ISSUER)
        .withAudience(AUDIENCE)
        .build()
    companion object {
        private const val ISSUER = "knet"
        private const val AUDIENCE = "knet.kr"
    }
}