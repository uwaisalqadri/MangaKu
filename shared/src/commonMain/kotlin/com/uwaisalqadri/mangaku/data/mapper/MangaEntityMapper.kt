package com.uwaisalqadri.mangaku.data.mapper

import com.uwaisalqadri.mangaku.data.source.db.entity.MangaEntity
import com.uwaisalqadri.mangaku.domain.base.model.Image
import com.uwaisalqadri.mangaku.domain.base.model.Manga
import com.uwaisalqadri.mangaku.utils.emptyString

fun List<MangaEntity>.map(): List<Manga> {
    return map { it.map() }
}

fun Manga.map(): MangaEntity {
    return MangaEntity(
        id = id,
        type = type,
        ageRating = ageRating,
        ageRatingGuide = ageRatingGuide,
        averageRating = averageRating,
        canonicalTitle = canonicalTitle,
        chapterCount = chapterCount.toLong(),
        description = description,
        endDate = endDate,
        favoritesCount = favoritesCount.toLong(),
        mangaType = mangaType,
        nextRelease = nextRelease,
        popularityRank = popularityRank,
        ratingRank = ratingRank.toLong(),
        serialization = serialization,
        slug = slug,
        startDate = startDate,
        status = status,
        subtype = subtype,
        synopsis = synopsis,
        tba = tba,
        userCount = userCount.toLong(),
        volumeCount = volumeCount.toLong(),
        image = posterImage.original
    )
}

fun MangaEntity.map(): Manga {
    return Manga(
        id = id,
        type = type,
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
        mangaType = emptyString(),
        coverImage = Image(original = image.orEmpty()),
        posterImage = Image(original = image.orEmpty())
    )
}
