package com.uwaisalqadri.mangaku.utils

import com.uwaisalqadri.mangaku.domain.model.Manga
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDate
import kotlinx.datetime.toLocalDateTime
import kotlin.math.roundToInt

// e.g 2010-06-01T22:19:44.475Z, only support ISO 8601 format
fun String.toLocalDate(): String {
    val dateTimeZone = toInstant().toLocalDateTime(TimeZone.currentSystemDefault()).date
    val dateString = dateTimeZone.toString().toLocalDate()
    return dateString.toString()
}

fun Double.toFiveStars(): Int {
    return roundToInt() / 2 / 10 - 1
}

fun Manga.getTitle(): String {
    val title = attributes?.titles
    return title?.ja_jp ?: title?.en_jp ?: title?.en_us ?: title?.en ?: ""
}

fun Manga.getPosterImage(): String {
    val posterImage = attributes?.posterImage
    return posterImage?.original ?: posterImage?.large ?: posterImage?.medium ?: ""
}

fun Manga.getCoverImage(): String {
    val coverImage = attributes?.coverImage
    return coverImage?.original ?: coverImage?.large ?: coverImage?.medium ?: ""
}









