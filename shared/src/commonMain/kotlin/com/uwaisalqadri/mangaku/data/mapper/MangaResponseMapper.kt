package com.uwaisalqadri.mangaku.data.mapper

import com.uwaisalqadri.mangaku.data.source.api.response.*
import com.uwaisalqadri.mangaku.domain.base.model.*

fun List<MangaItemResponse>.map(): List<Manga> {
    return map { it.map() }
}

fun MangaItemResponse?.map(): Manga {
    return Manga(
        attributes = this?.attributes?.map(),
        id = this?.id.orEmpty(),
        type = this?.type.orEmpty()
    )
}

fun AttributesResponse.map(): Attributes {
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

fun TitlesResponse.map(): Titles {
    return Titles(
        en = en ?: "",
        enJp = en_jp ?: "",
        enUs = en_us ?: "",
        jaJp = ja_jp ?: ""
    )
}


fun PosterImageResponse.map(): PosterImage {
    return PosterImage(
        large = large.orEmpty(),
        medium = medium.orEmpty(),
        original = original.orEmpty(),
        small = small.orEmpty(),
        tiny = tiny.orEmpty()
    )
}


fun CoverImageResponse.map(): CoverImage {
    return CoverImage(
        large = large,
        medium = medium,
        original = original,
        small = small,
        tiny = tiny
    )
}
