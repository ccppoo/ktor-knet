package com.example.utils

import java.time.*
import java.time.format.DateTimeFormatter

object T {
    val UTC = ZoneId.of("UTC")
    val KOREA = ZoneId.of("Asia/Seoul")

    val TimeFormat_1 = "yyyy-MM-dd HH:mm:ss"
    val TimeFormat_2 = "yyyy-MM-dd HH:mm:ss.SSSSSS"
    val TimeFormat_3 = "yyyy-MM-dd HH:mm"
    val TimeFormat_4 = "yyyyMMddHHmm"

    fun getDateTime(pattern : String = TimeFormat_1, timezone : ZoneId = UTC) : String =
        DateTimeFormatter.ofPattern(pattern).withZone(timezone).format(Instant.now())
}