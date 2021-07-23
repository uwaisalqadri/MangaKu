package com.uwaisalqadri.mangaapp.data.souce.remote.response

import kotlinx.serialization.Serializable

@Serializable
data class Attributes(
    val ageRating: String,
    val ageRatingGuide: String,
    val averageRating: String,
    val canonicalTitle: String,
    val chapterCount: Int,
    val coverImage: String,
    val coverImageTopOffset: Int,
    val createdAt: String,
    val description: String,
    val endDate: String,
    val favoritesCount: Int,
    val mangaType: String,
    val nextRelease: String,
    val popularityRank: Int,
    val posterImage: PosterImage,
    val ratingFrequencies: RatingFrequencies,
    val ratingRank: Int,
    val serialization: String,
    val slug: String,
    val startDate: String,
    val status: String,
    val subtype: String,
    val synopsis: String,
    val tba: String,
    val titles: Titles,
    val updatedAt: String,
    val userCount: Int,
    val volumeCount: Int
)