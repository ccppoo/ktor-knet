package com.example.auth

import io.ktor.server.auth.*

class UserPrinciple(val username : String) : Principal