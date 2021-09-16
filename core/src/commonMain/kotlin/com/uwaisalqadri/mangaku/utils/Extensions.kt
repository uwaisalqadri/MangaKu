package com.uwaisalqadri.mangaku.utils

import com.uwaisalqadri.mangaku.domain.model.Manga
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDate
import kotlinx.datetime.toLocalDateTime
import kotlin.math.roundToInt

object Extensions {
    // e.g 2010-06-01T22:19:44.475Z, only support ISO 8601 format
    fun toLocalDate(date: String): String {
        val dateTimeZone = date.toInstant().toLocalDateTime(TimeZone.currentSystemDefault()).date
        val dateString = dateTimeZone.toString().toLocalDate()
        return dateString.toString()
    }

    fun toFiveStars(avgRating: Double): Int {
        return avgRating.roundToInt() / 2 / 10 - 1
    }

    fun getTitle(manga: Manga): String {
        val title = manga.attributes?.titles
        return title?.ja_jp ?: title?.en_jp ?: title?.en_us ?: title?.en ?: ""
    }

    fun getPosterImage(manga: Manga): String {
        val posterImage = manga.attributes?.posterImage
        return posterImage?.original ?: posterImage?.large ?: posterImage?.medium ?: ""
    }

    fun getCoverImage(manga: Manga): String {
        val coverImage = manga.attributes?.coverImage
        val posterImage = manga.attributes?.posterImage
        return coverImage?.original ?: coverImage?.large ?: coverImage?.medium ?: posterImage?.original ?: ""
    }
}









