package com.uwaisalqadri.mangaku.utils

import com.uwaisalqadri.mangaku.domain.base.model.Image
import com.uwaisalqadri.mangaku.domain.base.model.Manga
import kotlin.math.roundToInt

fun emptyString(): String = ""

fun Double.toFiveStars(): Int =
    (this / 2 / 10 - 1).roundToInt()

val Manga.title: String get() =
    canonicalTitle
        ?: titles?.jaJp
        ?: titles?.enJp
        ?: titles?.enUs
        ?: titles?.en
        ?: emptyString()

val Image?.url: String get() {
    return this?.original
        ?: this?.large
        ?: this?.medium
        ?: this?.original
        ?: emptyString()
}