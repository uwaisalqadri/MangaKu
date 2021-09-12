package com.uwaisalqadri.mangaku.utils

import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDate
import kotlinx.datetime.toLocalDateTime

object Extensions {
    fun fromKotlinDate(date: String): String {
        val dateTimeZone = date.toInstant().toLocalDateTime(TimeZone.currentSystemDefault()).date
        val dateString = dateTimeZone.toString().toLocalDate()
        return dateString.toString()
    }
}