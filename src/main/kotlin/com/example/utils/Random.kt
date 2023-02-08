package com.example.utils

import kotlin.random.Random

object R {

    fun int(from : Int, to : Int) : Int {
        return Random.nextInt(from, to)
    }
}