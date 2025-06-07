package com.uwaisalqadri.mangaku.utils

import com.uwaisalqadri.mangaku.domain.base.model.Manga
import kotlin.math.roundToInt

fun emptyString(): String = ""

fun Double.toFiveStars(): Int =
    (this / 2 / 10 - 1).roundToInt()

fun Manga.getTitle(): String =
    canonicalTitle
        ?: titles?.jaJp
        ?: titles?.enJp
        ?: titles?.enUs
        ?: titles?.en
        ?: emptyString()

fun Manga.getPosterImage(): String {
    val poster = posterImage
    return poster?.original
        ?: poster?.large
        ?: poster?.medium
        ?: emptyString()
}

fun Manga.getCoverImage(): String {
    val cover = coverImage
    val poster = posterImage
    return cover?.original
        ?: cover?.large
        ?: cover?.medium
        ?: poster?.original
        ?: emptyString()
}