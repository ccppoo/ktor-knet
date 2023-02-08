package com.example.plugins

import com.auth0.jwt.interfaces.DecodedJWT
import com.auth0.jwt.interfaces.JWTVerifier
import io.ktor.http.auth.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*


public fun AuthenticationConfig.cookieJWT(
    name: String? = null,
    configure: CookieJWTProvider.Config.() -> Unit
) {
    val provider = CookieJWTProvider.Config(name).apply(configure).build()
    register(provider)
}

class CookieJWTProvider internal constructor(config: Config) : AuthenticationProvider(config) {

    private val challengeFunction: CookieJWTAuthChallengeFunction = config.challenge
    private val authenticationFunction = config.authenticationFunction
    private val verifier: JWTVerifier = config.verifier!!
    override suspend fun onAuthenticate(context: AuthenticationContext) {
        val call = context.call
        val cookie = call.request.cookies

        if (cookie.get("cat") == null){
            // challenge 이거 호출하면 안되는거 (인증 실패)
            context.challenge("fail!!", AuthenticationFailedCause.NoCredentials) { challenge, call ->
                challengeFunction(CookieJWTChallengeContext(call),"world")
                if (!challenge.completed && call.response.status() != null) {
                    challenge.complete()
                }
            }
            return
        }

        try {
            val cookieJWToken : String = cookie["cat"]!!
//            println("cookieJWToken : ${cookieJWToken}")
            val decodedToken = verifier.verify(cookieJWToken)

//            println("issuer : ${decodedToken.issuer}")
//            println("signature : ${decodedToken.signature}")
            val principal = authenticationFunction(call, decodedToken)

//            println(decodedToken.issuer)
//            println("principal : ${principal}")
//            val principal = UserPrinciple(username = "test")
            if (principal != null) {
                context.principal(name, principal)
                return
            }
//            context.bearerChallenge(
//                AuthenticationFailedCause.InvalidCredentials,
//                realm,
//                schemes,
//                challengeFunction
//            )
        } catch (cause: Throwable) {
            // JWTVerificationException
            val message = cause.message ?: cause.javaClass.simpleName
            context.error("keyyy!", AuthenticationFailedCause.Error(message))
        }
    }

    public class Config internal constructor(name: String?) : AuthenticationProvider.Config(name) {
        internal var authenticationFunction: AuthenticationFunction<DecodedJWT> = {
            throw NotImplementedError(
                "JWT auth validate function is not specified. Use jwt { validate { ... } } to fix."
            )
        }
        internal var verifier: (JWTVerifier?) =  null

        internal var challenge: CookieJWTAuthChallengeFunction = { hello ->
            call.respond(
                UnauthorizedResponse(
                    HttpAuthHeader.Parameterized(
                        "CookieJWT",
                        mapOf(HttpAuthHeader.Parameters.Realm to hello)
                    )
                )
            )
        }

        internal fun build() = CookieJWTProvider(this)

        public fun verifier(verifier: JWTVerifier) {
            this.verifier = verifier
        }
        public fun validate(validate: suspend ApplicationCall.(DecodedJWT) -> Principal?) {
            authenticationFunction = validate
        }
    }
}

public class CookieJWTChallengeContext(
    public val call: ApplicationCall
)


public typealias CookieJWTAuthChallengeFunction =
        suspend CookieJWTChallengeContext.(hello : String) -> Unit