package com.uwaisalqadri.mangaku.utils

import com.uwaisalqadri.mangaku.domain.model.Manga
import com.uwaisalqadri.mangaku.domain.model.Titles
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDate
import kotlinx.datetime.toLocalDateTime
import kotlin.math.roundToInt

fun Double.toFiveStars(): Int {
    return roundToInt() / 2 / 10 - 1
}

fun Manga.getTitle(): String {
    return attributes?.canonicalTitle ?: attributes?.titles?.ja_jp ?:
    attributes?.titles?.en_jp ?: attributes?.titles?.en_us ?: attributes?.titles?.en ?: ""
}

fun Manga.getPosterImage(): String {
    val posterImage = attributes?.posterImage
    return posterImage?.original ?: posterImage?.large ?: posterImage?.medium ?: ""
}

fun Manga.getCoverImage(): String {
    val coverImage = attributes?.coverImage
    val posterImage = attributes?.posterImage
    return coverImage?.original ?: coverImage?.large ?: coverImage?.medium ?: posterImage?.original ?: ""
}