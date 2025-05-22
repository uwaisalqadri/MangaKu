package com.uwaisalqadri.mangaku.data.source.remote.response

import kotlinx.serialization.Serializable

@Serializable
data class AttributesResponse(
    val ageRating: String? = "",
    val ageRatingGuide: String? = "",
    val averageRating: Double? = 0.0,
    val canonicalTitle: String? = "",
    val chapterCount: Int? = 0,
    val coverImage: CoverImageResponse? = null,
    val description: String? = "",
    val endDate: String? = "",
    val favoritesCount: Int? = 0,
    val mangaType: String? = "",
    val nextRelease: String? = "",
    val popularityRank: String? = "",
    val posterImage: PosterImageResponse? = null,
    val ratingRank: Int? = 0,
    val serialization: String? = "",
    val slug: String? = "",
    val startDate: String? = "",
    val status: String? = "",
    val subtype: String? = "",
    val synopsis: String? = "",
    val tba: String? = "",
    val titles: TitlesResponse? = null,
    val userCount: Int? = 0,
    val volumeCount: Int? = 0
)