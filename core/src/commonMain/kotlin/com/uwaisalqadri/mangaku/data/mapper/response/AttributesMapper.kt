package com.uwaisalqadri.mangaku.data.mapper.response

import com.uwaisalqadri.mangaku.data.mapper.BaseMapper
import com.uwaisalqadri.mangaku.data.souce.remote.response.AttributesItem
import com.uwaisalqadri.mangaku.domain.model.Attributes

class AttributesMapper(
    private val coverImageMapper: CoverImageMapper,
    private val posterImageMapper: PosterImageMapper,
    private val titlesMapper: TitlesMapper
): BaseMapper<AttributesItem, Attributes> {

    override fun mapToDomain(model: AttributesItem): Attributes {
        return Attributes(
            model.ageRating ?: "",
            model.ageRatingGuide ?: "",
            model.averageRating ?: 0.0,
            model.canonicalTitle ?: "",
            model.chapterCount ?: 0,
            model.coverImage?.let { coverImageMapper.mapToDomain(it) },
            model.description ?: "",
            model.endDate ?: "",
            model.favoritesCount ?: 0,
            model.mangaType ?: "",
            model.nextRelease ?: "",
            model.popularityRank ?: "",
            model.posterImage?.let { posterImageMapper.mapToDomain(it) },
            model.ratingRank ?: 0,
            model.serialization ?: "",
            model.slug ?: "",
            model.startDate ?: "",
            model.status ?: "",
            model.subtype ?: "",
            model.synopsis ?: "",
            model.tba ?: "",
            model.titles?.let { titlesMapper.mapToDomain(it) },
            model.userCount ?: 0,
            model.volumeCount ?: 0
        )
    }

    override fun mapToModel(domain: Attributes): AttributesItem {
        return AttributesItem(
            domain.ageRating,
            domain.ageRatingGuide,
            domain.averageRating,
            domain.canonicalTitle,
            domain.chapterCount,
            domain.coverImage?.let { coverImageMapper.mapToModel(it) },
            domain.description,
            domain.endDate,
            domain.favoritesCount,
            domain.mangaType,
            domain.nextRelease,
            domain.popularityRank,
            domain.posterImage?.let { posterImageMapper.mapToModel(it) },
            domain.ratingRank,
            domain.serialization,
            domain.slug,
            domain.startDate,
            domain.status,
            domain.subtype,
            domain.synopsis,
            domain.tba,
            domain.titles?.let { titlesMapper.mapToModel(it) },
            domain.userCount,
            domain.volumeCount
        )
    }
}