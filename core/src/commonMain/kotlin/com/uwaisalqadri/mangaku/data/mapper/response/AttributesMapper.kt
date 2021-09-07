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
            model.averageRating ?: "",
            model.canonicalTitle ?: "",
            model.chapterCount ?: "",
            model.coverImage?.let { coverImageMapper.mapToDomain(it) },
            model.createdAt ?: "",
            model.description ?: "",
            model.endDate ?: "",
            model.favoritesCount ?: "",
            model.mangaType ?: "",
            model.nextRelease ?: "",
            model.popularityRank ?: "",
            model.posterImage?.let { posterImageMapper.mapToDomain(it) },
            model.ratingRank ?: "",
            model.serialization ?: "",
            model.slug ?: "",
            model.startDate ?: "",
            model.status ?: "",
            model.subtype ?: "",
            model.synopsis ?: "",
            model.tba ?: "",
            model.titles?.let { titlesMapper.mapToDomain(it) },
            model.updatedAt ?: "",
            model.userCount ?: "",
            model.volumeCount ?: ""
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
            domain.createdAt,
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
            domain.updatedAt,
            domain.userCount,
            domain.volumeCount
        )
    }
}