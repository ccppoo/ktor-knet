package com.example

import com.example.model.config.SecurityConfig
import io.ktor.server.application.*
import io.ktor.server.netty.*
import com.example.plugins.*
import java.io.File
import java.nio.file.Paths

// EngineMain 사용하는 경우 application.yaml을 사용
// embeddedServer 의 경우 gradle에서 설정을 해야하는 경우가 있음

fun main(args: Array<String>): Unit = EngineMain.main(args)

val Application.securityConfig : SecurityConfig
    get() = SecurityConfig(
    environment.config.property("jwt.secret").getString(),
    environment.config.property("jwt.audience").getString(),
    environment.config.property("jwt.realm").getString()
)

val Application.basePath : File
    get() = File(Paths.get("").toAbsolutePath().toString())

fun Application.module() {
    configureSerialization()
    configureDatabases()
    configureAdministration()
    configureMonitoring()
    configureHTTP()
    configureSecurity()
    configureRouting()
}

