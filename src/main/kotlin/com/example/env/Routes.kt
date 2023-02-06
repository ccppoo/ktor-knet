package com.example.env

import io.ktor.resources.Resource

/**
 * 둘이 초기화 시점의 차이를 가짐
 * object : 싱글턴 클래스라고 생각 ㅇㅇ, class가 사용될 때 초기화 됨(lazy init)
 * companion object : 속해 있는 클래스 생성시 같이 초기화 됨(init)
 *
 * 싱글톤이기 때문에 config 설정하기 좋다 ㅇㅇ
 */

object Routes {

    @Resource("/ping")
    class Ping

}