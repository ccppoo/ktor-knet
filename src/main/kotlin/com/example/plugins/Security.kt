package com.example.plugins

import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.example.auth.AuthController
import com.example.auth.UserPrinciple
import com.example.securityConfig
import io.ktor.server.application.*


fun Application.configureSecurity(
    jwtController: AuthController = AuthController(this.securityConfig)
) {
    
    authentication {
        jwt {
            verifier(jwtController.verifier)

            validate { credential ->
                if (credential.issuer != null) UserPrinciple(credential.issuer!!) else null
            }
        }
    }
}
