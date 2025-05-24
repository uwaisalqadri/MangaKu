package com.uwaisalqadri.mangaku.data.mapper

import com.uwaisalqadri.mangaku.db.MangaEntity
import com.uwaisalqadri.mangaku.domain.model.Attributes
import com.uwaisalqadri.mangaku.domain.model.CoverImage
import com.uwaisalqadri.mangaku.domain.model.Manga
import com.uwaisalqadri.mangaku.domain.model.PosterImage
import com.uwaisalqadri.mangaku.domain.model.Titles
import com.uwaisalqadri.mangaku.utils.emptyString

fun List<MangaEntity>.map(): List<Manga> {
    return map { it.map() }
}

fun Manga.map(): MangaEntity {
    return MangaEntity(
        id = id,
        type = type,
        ageRating = attributes?.ageRating,
        ageRatingGuide = attributes?.ageRatingGuide,
        averageRating = attributes?.averageRating,
        canonicalTitle = attributes?.canonicalTitle,
        chapterCount = attributes?.chapterCount?.toLong(),
        description = attributes?.description,
        endDate = attributes?.endDate,
        favoritesCount = attributes?.favoritesCount?.toLong(),
        mangaType = attributes?.mangaType,
        nextRelease = attributes?.nextRelease,
        popularityRank = attributes?.popularityRank,
        ratingRank = attributes?.ratingRank?.toLong(),
        serialization = attributes?.serialization,
        slug = attributes?.slug,
        startDate = attributes?.startDate,
        status = attributes?.status,
        subtype = attributes?.subtype,
        synopsis = attributes?.synopsis,
        tba = attributes?.tba,
        userCount = attributes?.userCount?.toLong(),
        volumeCount = attributes?.volumeCount?.toLong()
    )
}

fun MangaEntity.map(): Manga {
    return Manga(
        id = id,
        type = type,
        attributes = Attributes(
            ageRating = ageRating.orEmpty(),
            ageRatingGuide = ageRatingGuide.orEmpty(),
            averageRating = averageRating ?: 0.0,
            canonicalTitle = canonicalTitle.orEmpty(),
            chapterCount = chapterCount?.toInt() ?: 0,
            description = description.orEmpty(),
            endDate = endDate.orEmpty(),
            favoritesCount = favoritesCount?.toInt() ?: 0,
            nextRelease = nextRelease.orEmpty(),
            popularityRank = popularityRank.orEmpty(),
            ratingRank = ratingRank?.toInt() ?: 0,
            serialization = serialization.orEmpty(),
            slug = slug.orEmpty(),
            startDate = startDate.orEmpty(),
            status = status.orEmpty(),
            subtype = subtype.orEmpty(),
            synopsis = synopsis.orEmpty(),
            tba = tba.orEmpty(),
            userCount = userCount?.toInt() ?: 0,
            volumeCount = volumeCount?.toInt() ?: 0,
            mangaType = emptyString()
        )
    )
}