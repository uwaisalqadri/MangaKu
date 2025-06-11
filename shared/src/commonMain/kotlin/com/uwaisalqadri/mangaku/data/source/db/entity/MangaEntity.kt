package com.uwaisalqadri.mangaku.data.source.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MangaEntity(
    @PrimaryKey val id: String,
    val type: String,
    val image: String?,
    val ageRating: String?,
    val ageRatingGuide: String?,
    val averageRating: Double?,
    val canonicalTitle: String?,
    val chapterCount: Long?,
    val description: String?,
    val endDate: String?,
    val favoritesCount: Long?,
    val mangaType: String?,
    val nextRelease: String?,
    val popularityRank: String?,
    val ratingRank: Long?,
    val serialization: String?,
    val slug: String?,
    val startDate: String?,
    val status: String?,
    val subtype: String?,
    val synopsis: String?,
    val tba: String?,
    val userCount: Long?,
    val volumeCount: Long?,
)