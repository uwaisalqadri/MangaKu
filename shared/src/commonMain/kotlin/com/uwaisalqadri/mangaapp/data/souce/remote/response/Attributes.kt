package com.uwaisalqadri.mangaapp.data.souce.remote.response

import kotlinx.serialization.Serializable

@Serializable
data class Attributes(
    val ageRating: String,
    val ageRatingGuide: String,
    val averageRating: String,
    val canonicalTitle: String,
    val chapterCount: String,
    val coverImage: CoverImage? = null,
    val coverImageTopOffset: String,
    val createdAt: String,
    val description: String,
    val endDate: String,
    val favoritesCount: String,
    val mangaType: String,
    val nextRelease: String,
    val popularityRank: String,
    val posterImage: PosterImage,
    val ratingFrequencies: RatingFrequencies,
    val ratingRank: String,
    val serialization: String,
    val slug: String,
    val startDate: String,
    val status: String,
    val subtype: String,
    val synopsis: String,
    val tba: String,
    val titles: Titles,
    val updatedAt: String,
    val userCount: String,
    val volumeCount: String
)