package com.uwaisalqadri.mangaku.domain.mapper

import com.uwaisalqadri.mangaku.data.souce.remote.response.*
import com.uwaisalqadri.mangaku.domain.model.*

fun List<MangaItem>.map(): List<Manga> {
    return map { it.map() }
}

fun MangaItem.map(): Manga {
    return Manga(
        attributes = attributes?.let { it.map() },
        id = id,
        type = type
    )
}

fun AttributesItem.map(): Attributes {
    return Attributes(
        ageRating = ageRating ?: "",
        ageRatingGuide = ageRatingGuide ?: "",
        averageRating = averageRating ?: 0.0,
        canonicalTitle = canonicalTitle ?: "",
        chapterCount = chapterCount ?: 0,
        coverImage = coverImage?.let { it.map() },
        description = description ?: "",
        endDate = endDate ?: "",
        favoritesCount = favoritesCount ?: 0,
        mangaType = mangaType ?: "",
        nextRelease = nextRelease ?: "",
        popularityRank = popularityRank ?: "",
        posterImage = posterImage?.let { it.map() },
        ratingRank = ratingRank ?: 0,
        serialization = serialization ?: "",
        slug = slug ?: "",
        startDate = startDate ?: "",
        status = status ?: "",
        subtype = subtype ?: "",
        synopsis = synopsis ?: "",
        tba = tba ?: "",
        titles = titles?.let { it.map() },
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
        large = large ?: "",
        medium = medium ?: "",
        original = original ?: "",
        small = small ?: "",
        tiny = tiny ?: ""
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