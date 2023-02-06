package com.example

import io.ktor.server.application.*
import io.ktor.server.netty.*
import com.example.plugins.*

// EngineMain 사용하는 경우 application.yaml을 사용
// embeddedServer 의 경우 gradle에서 설정을 해야하는 경우가 있음

fun main(args: Array<String>): Unit = EngineMain.main(args)

fun Application.module() {
    configureSerialization()
    configureDatabases()
    configureAdministration()
    configureMonitoring()
    configureHTTP()
    configureSecurity()
    configureRouting()
}

