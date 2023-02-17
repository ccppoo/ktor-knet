package com.example.model.request

import kotlinx.serialization.Serializable

// JSON 형태로 받으려면 이렇게
@Serializable
data class SampleJsonFormat (val temp : String)


//    mutableListOf<Map<String, Any>>

// formdata 형태로 받는 경우
class MediaMultipartData(val items: List<Any>) {

        override fun toString() : String {

            return items.joinToString { it-> it.toString() }
        }
}

