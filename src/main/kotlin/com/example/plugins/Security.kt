package com.example.plugins

import io.ktor.server.auth.*
import com.example.auth.AuthController
import com.example.auth.UserPrinciple
import com.example.securityConfig
import io.ktor.server.application.*

fun Application.configureSecurity(
    jwtController: AuthController = AuthController(this.securityConfig)
) {
    authentication {
        cookieJWT {
            verifier(jwtController.verifier)
            validate { decodedJWT ->
                if ((decodedJWT.issuer == "knet") && (decodedJWT.audience.contains("knet.kr"))) {
                    return@validate UserPrinciple(decodedJWT.subject)
                }
                else null
            }
        }
//        jwt {
//            verifier(jwtController.verifier)
//
//            validate { credential ->
//                if (credential.issuer != null) UserPrinciple(credential.issuer!!) else null
//            }
//        }
    }
}
