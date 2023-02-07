package com.example.model.URLResource

import io.ktor.resources.*

@Resource("/img")
class StaticImages(){

    @Resource("{name}")
    class withName(val parent: StaticImages = StaticImages(), val name : String?)

}


sealed class A(){

}