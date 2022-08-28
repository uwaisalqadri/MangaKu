package com.uwaisalqadri.mangaku.data.souce.remote.response

import kotlinx.serialization.Serializable

@Serializable
data class AttributesItem(
    val ageRating: String? = "",
    val ageRatingGuide: String? = "",
    val averageRating: Double? = 0.0,
    val canonicalTitle: String? = "",
    val chapterCount: Int? = 0,
    val coverImage: CoverImageItem? = null,
    val description: String? = "",
    val endDate: String? = "",
    val favoritesCount: Int? = 0,
    val mangaType: String? = "",
    val nextRelease: String? = "",
    val popularityRank: String? = "",
    val posterImage: PosterImageItem? = null,
    val ratingRank: Int? = 0,
    val serialization: String? = "",
    val slug: String? = "",
    val startDate: String? = "",
    val status: String? = "",
    val subtype: String? = "",
    val synopsis: String? = "",
    val tba: String? = "",
    val titles: TitlesItem? = null,
    val userCount: Int? = 0,
    val volumeCount: Int? = 0
)