package com.uwaisalqadri.mangaku.domain.mapper

import com.uwaisalqadri.mangaku.data.souce.remote.response.*
import com.uwaisalqadri.mangaku.domain.model.*

fun List<MangaItem>.map(): List<Manga> {
    return map { it.map() }
}

fun MangaItem.map(): Manga {
    return Manga(
        attributes = attributes?.map(),
        id = id,
        type = type
    )
}

fun AttributesItem.map(): Attributes {
    return Attributes(
        ageRating = ageRating.orEmpty(),
        ageRatingGuide = ageRatingGuide.orEmpty(),
        averageRating = averageRating ?: 0.0,
        canonicalTitle = canonicalTitle.orEmpty(),
        chapterCount = chapterCount ?: 0,
        coverImage = coverImage?.map(),
        description = description.orEmpty(),
        endDate = endDate.orEmpty(),
        favoritesCount = favoritesCount ?: 0,
        mangaType = mangaType.orEmpty(),
        nextRelease = nextRelease.orEmpty(),
        popularityRank = popularityRank.orEmpty(),
        posterImage = posterImage?.map(),
        ratingRank = ratingRank ?: 0,
        serialization = serialization.orEmpty(),
        slug = slug.orEmpty(),
        startDate = startDate.orEmpty(),
        status = status.orEmpty(),
        subtype = subtype.orEmpty(),
        synopsis = synopsis.orEmpty(),
        tba = tba.orEmpty(),
        titles = titles?.map(),
        userCount = userCount ?: 0,
        volumeCount = volumeCount ?: 0
    )
}

fun TitlesItem.map(): Titles {
    return Titles(
        en = en ?: "",
        en_jp = en_jp ?: "",
        en_us = en_us ?: "",
        ja_jp = ja_jp ?: ""
    )
}


fun PosterImageItem.map(): PosterImage {
    return PosterImage(
        large = large.orEmpty(),
        medium = medium.orEmpty(),
        original = original.orEmpty(),
        small = small.orEmpty(),
        tiny = tiny.orEmpty()
    )
}


fun CoverImageItem.map(): CoverImage {
    return CoverImage(
        large = large,
        medium = medium,
        original = original,
        small = small,
        tiny = tiny
    )
}