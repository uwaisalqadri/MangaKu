package com.uwaisalqadri.mangaku.domain.mapper

import com.uwaisalqadri.mangaku.data.source.local.entity.*
import com.uwaisalqadri.mangaku.domain.model.*

fun List<MangaObject>.map(): List<Manga> {
    return map { it.map() }
}

fun MangaObject.map(): Manga {
    return Manga(
        attributes = attributes?.map(),
        id = id,
        type = type
    )
}

fun Manga.map(): MangaObject {
    return MangaObject().apply {
        attributes = this@map.attributes?.map()
        id = this@map.id
        type = this@map.type
    }
}

fun AttributesObject.map(): Attributes {
    return Attributes(
        ageRating = ageRating,
        ageRatingGuide = ageRatingGuide,
        averageRating = averageRating,
        canonicalTitle = canonicalTitle,
        chapterCount = chapterCount,
        coverImage = coverImage?.map(),
        description = description,
        endDate = endDate,
        favoritesCount = favoritesCount,
        mangaType = mangaType,
        nextRelease = nextRelease,
        popularityRank = popularityRank,
        posterImage = posterImage?.map(),
        ratingRank = ratingRank,
        serialization = serialization,
        slug = slug,
        startDate = startDate,
        status = status,
        subtype = subtype,
        synopsis = synopsis,
        tba = tba,
        titles = titles?.map(),
        userCount = userCount,
        volumeCount = volumeCount
    )
}

fun Attributes.map(): AttributesObject {
    return AttributesObject().apply {
        ageRating = this@map.ageRating
        ageRatingGuide = this@map.ageRatingGuide
        averageRating = this@map.averageRating
        canonicalTitle = this@map.canonicalTitle
        chapterCount = this@map.chapterCount
        coverImage = this@map.coverImage?.map()
        description = this@map.description
        endDate = this@map.endDate
        favoritesCount = this@map.favoritesCount
        mangaType = this@map.mangaType
        nextRelease = this@map.nextRelease
        popularityRank = this@map.popularityRank
        posterImage = this@map.posterImage?.map()
        ratingRank = this@map.ratingRank
        serialization = this@map.serialization
        slug = this@map.slug
        startDate = this@map.startDate
        status = this@map.status
        subtype = this@map.subtype
        synopsis = this@map.synopsis
        tba = this@map.tba
        titles = this@map.titles?.map()
        userCount = this@map.userCount
        volumeCount = this@map.volumeCount
    }
}

fun TitlesObject.map(): Titles {
    return Titles(
        en = en,
        en_jp = en_jp,
        en_us = en_us,
        ja_jp = ja_jp
    )
}

fun Titles.map(): TitlesObject {
    return TitlesObject().apply {
        en = this@map.en
        en_jp = this@map.en_jp
        en_us = this@map.en_us
        ja_jp = this@map.ja_jp
    }
}

fun CoverImageObject.map(): CoverImage {
    return CoverImage(
        large = large,
        medium = medium,
        original = original,
        small = small,
        tiny = tiny
    )
}

fun CoverImage.map(): CoverImageObject {
    return CoverImageObject().apply {
        large = this@map.large.toString()
        medium = this@map.medium.toString()
        original = this@map.original.toString()
        small = this@map.small.toString()
        tiny = this@map.tiny.toString()
    }
}

fun PosterImageObject.map(): PosterImage {
    return PosterImage(
        large = large,
        medium = medium,
        original = original,
        small = small,
        tiny = tiny
    )
}

fun PosterImage.map(): PosterImageObject {
    return PosterImageObject().apply {
        large = this@map.large
        medium = this@map.medium
        original = this@map.original
        small = this@map.small
        tiny = this@map.tiny

    }
}