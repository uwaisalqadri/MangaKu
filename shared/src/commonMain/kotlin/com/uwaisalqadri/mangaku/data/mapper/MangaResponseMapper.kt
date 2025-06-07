package com.uwaisalqadri.mangaku.data.mapper

import com.uwaisalqadri.mangaku.data.source.api.response.*
import com.uwaisalqadri.mangaku.domain.base.model.*

fun List<MangaItemResponse>.map(): List<Manga> {
    return map { it.map() }
}

fun MangaItemResponse?.map(): Manga {
    return Manga(
        id = this?.id.orEmpty(),
        type = this?.type.orEmpty(),
        ageRating = this?.attributes?.ageRating.orEmpty(),
        ageRatingGuide = this?.attributes?.ageRatingGuide.orEmpty(),
        averageRating = this?.attributes?.averageRating ?: 0.0,
        canonicalTitle = this?.attributes?.canonicalTitle.orEmpty(),
        chapterCount = this?.attributes?.chapterCount ?: 0,
        coverImage = this?.attributes?.coverImage?.map(),
        description = this?.attributes?.description.orEmpty(),
        endDate = this?.attributes?.endDate.orEmpty(),
        favoritesCount = this?.attributes?.favoritesCount ?: 0,
        mangaType = this?.attributes?.mangaType.orEmpty(),
        nextRelease = this?.attributes?.nextRelease.orEmpty(),
        popularityRank = this?.attributes?.popularityRank.orEmpty(),
        posterImage = this?.attributes?.posterImage?.map(),
        ratingRank = this?.attributes?.ratingRank ?: 0,
        serialization = this?.attributes?.serialization.orEmpty(),
        slug = this?.attributes?.slug.orEmpty(),
        startDate = this?.attributes?.startDate.orEmpty(),
        status = this?.attributes?.status.orEmpty(),
        subtype = this?.attributes?.subtype.orEmpty(),
        synopsis = this?.attributes?.synopsis.orEmpty(),
        tba = this?.attributes?.tba.orEmpty(),
        titles = this?.attributes?.titles?.map(),
        userCount = this?.attributes?.userCount ?: 0,
        volumeCount = this?.attributes?.volumeCount ?: 0
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

fun PosterImageResponse.map(): Image {
    return Image(
        large = large.orEmpty(),
        medium = medium.orEmpty(),
        original = original.orEmpty(),
        small = small.orEmpty(),
        tiny = tiny.orEmpty()
    )
}

fun CoverImageResponse.map(): Image {
    return Image(
        large = large.orEmpty(),
        medium = medium.orEmpty(),
        original = original.orEmpty(),
        small = small.orEmpty(),
        tiny = tiny.orEmpty()
    )
}