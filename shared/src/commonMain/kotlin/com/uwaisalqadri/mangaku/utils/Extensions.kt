package com.uwaisalqadri.mangaku.utils

import com.uwaisalqadri.mangaku.domain.model.Manga
import com.uwaisalqadri.mangaku.domain.model.Titles
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDate
import kotlinx.datetime.toLocalDateTime
import kotlin.math.roundToInt

fun emptyString(): String = ""

fun Double.toFiveStars(): Int =
    (this / 2 / 10 - 1).roundToInt()

fun Manga.getTitle(): String =
    attributes?.canonicalTitle
        ?: attributes?.titles?.jaJp
        ?: attributes?.titles?.enJp
        ?: attributes?.titles?.enUs
        ?: attributes?.titles?.en
        ?: emptyString()

fun Manga.getPosterImage(): String {
    val poster = attributes?.posterImage
    return poster?.original
        ?: poster?.large
        ?: poster?.medium
        ?: emptyString()
}

fun Manga.getCoverImage(): String {
    val cover = attributes?.coverImage
    val poster = attributes?.posterImage
    return cover?.original
        ?: cover?.large
        ?: cover?.medium
        ?: poster?.original
        ?: emptyString()
}