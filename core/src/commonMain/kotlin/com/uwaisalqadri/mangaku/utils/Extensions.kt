package com.uwaisalqadri.mangaku.utils

import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDate
import kotlinx.datetime.toLocalDateTime

// share code for iOS
object Extensions {

    // e.g 2010-06-01T22:19:44.475Z, only support ISO 8601 format
    fun toLocalDate(date: String): String {
        val dateTimeZone = date.toInstant().toLocalDateTime(TimeZone.currentSystemDefault()).date
        val dateString = dateTimeZone.toString().toLocalDate()
        return dateString.toString()
    }

    fun toFiveStars(avgRating: Double) {
        val count = avgRating.toInt()
    }
}