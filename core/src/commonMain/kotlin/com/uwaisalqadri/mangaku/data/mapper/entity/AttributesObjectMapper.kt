package com.uwaisalqadri.mangaku.data.mapper.entity

import com.uwaisalqadri.mangaku.data.mapper.BaseMapper
import com.uwaisalqadri.mangaku.data.souce.local.entity.AttributesObject
import com.uwaisalqadri.mangaku.domain.model.Attributes

class AttributesObjectMapper(
    private val coverImageMapper: CoverImageObjectMapper,
    private val posterImageMapper: PosterImageObjectMapper,
    private val titlesMapper: TitlesObjectMapper
): BaseMapper<AttributesObject, Attributes> {

    override fun mapToDomain(model: AttributesObject): Attributes {
        return Attributes(
            model.ageRating,
            model.ageRatingGuide,
            model.averageRating,
            model.canonicalTitle,
            model.chapterCount,
            model.coverImage?.let { coverImageMapper.mapToDomain(it) },
            model.createdAt,
            model.description,
            model.endDate,
            model.favoritesCount,
            model.mangaType,
            model.nextRelease,
            model.popularityRank,
            model.posterImage?.let { posterImageMapper.mapToDomain(it) },
            model.ratingRank,
            model.serialization,
            model.slug,
            model.startDate,
            model.status,
            model.subtype,
            model.synopsis,
            model.tba,
            model.titles?.let { titlesMapper.mapToDomain(it) },
            model.updatedAt,
            model.userCount,
            model.volumeCount
        )
    }

    override fun mapToModel(domain: Attributes): AttributesObject {
        val attributesObject = AttributesObject()
        return attributesObject
    }
}