package com.uwaisalqadri.mangaku.data.souce.remote.response

import kotlinx.serialization.Serializable

@Serializable
data class AttributesItem(
    val ageRating: String,
    val ageRatingGuide: String,
    val averageRating: String,
    val canonicalTitle: String,
    val chapterCount: String,
    val coverImage: CoverImageItem? = null,
    val createdAt: String,
    val description: String,
    val endDate: String,
    val favoritesCount: String,
    val mangaType: String,
    val nextRelease: String,
    val popularityRank: String,
    val posterImage: PosterImageItem? = null,
    val ratingRank: String,
    val serialization: String,
    val slug: String,
    val startDate: String,
    val status: String,
    val subtype: String,
    val synopsis: String,
    val tba: String,
    val titles: TitlesItem? = null,
    val updatedAt: String,
    val userCount: String,
    val volumeCount: String
)