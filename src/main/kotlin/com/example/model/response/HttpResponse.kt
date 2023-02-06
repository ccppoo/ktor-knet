package com.example.model.response

import io.ktor.http.HttpStatusCode

/**
 * sealed class : 같은 패키지에서만 상속을 허용하는 abstract class의 더 빡센 조건ㅇㅇ
 * 컴파일러 입장에서 타입 제한되니깐 편하고, 생성자는 기본적으로 private 임
 */

sealed class HttpResponse<T : Response> {
    abstract val body: T
    abstract val code: HttpStatusCode

    data class Ok<T : Response>(override val body: T) : HttpResponse<T>() {
        override val code: HttpStatusCode = HttpStatusCode.OK
    }

    data class NotFound<T : Response>(override val body: T) : HttpResponse<T>() {
        override val code: HttpStatusCode = HttpStatusCode.NotFound
    }

    data class BadRequest<T : Response>(override val body: T) : HttpResponse<T>() {
        override val code: HttpStatusCode = HttpStatusCode.BadRequest
    }

    data class Unauthorized<T : Response>(override val body: T) : HttpResponse<T>() {
        override val code: HttpStatusCode = HttpStatusCode.Unauthorized
    }

    companion object {
        fun <T : Response> ok(response: T) = Ok(body = response)

        fun <T : Response> notFound(response: T) = NotFound(body = response)

        fun <T : Response> badRequest(response: T) = BadRequest(body = response)

        fun <T : Response> unauth(response: T) = Unauthorized(body = response)
    }
}

fun generateHttpResponse(response: Response): HttpResponse<Response> {
    return when (response.status) {
        State.SUCCESS -> HttpResponse.ok(response)
        State.NOT_FOUND -> HttpResponse.notFound(response)
        State.FAILED -> HttpResponse.badRequest(response)
        State.UNAUTHORIZED -> HttpResponse.unauth(response)
    }
}