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
        val attributes = AttributesObject()
        attributes.ageRating = domain.ageRating
        attributes.ageRatingGuide = domain.ageRatingGuide
        attributes.averageRating = domain.averageRating
        attributes.canonicalTitle = domain.canonicalTitle
        attributes.chapterCount = domain.chapterCount
        attributes.coverImage = domain.coverImage?.let { coverImageMapper.mapToModel(it) }
        attributes.createdAt = domain.createdAt
        attributes.description = domain.description
        attributes.endDate = domain.endDate
        attributes.favoritesCount = domain.favoritesCount
        attributes.mangaType = domain.mangaType
        attributes.nextRelease = domain.nextRelease
        attributes.popularityRank = domain.popularityRank
        attributes.posterImage = domain.posterImage?.let { posterImageMapper.mapToModel(it) }
        attributes.ratingRank = domain.ratingRank
        attributes.serialization = domain.serialization
        attributes.slug = domain.slug
        attributes.startDate = domain.startDate
        attributes.status = domain.status
        attributes.subtype = domain.subtype
        attributes.synopsis = domain.synopsis
        attributes.tba = domain.tba
        attributes.titles = domain.titles?.let { titlesMapper.mapToModel(it) }
        attributes.updatedAt = domain.updatedAt
        attributes.userCount = domain.userCount
        attributes.volumeCount = domain.volumeCount
        return attributes
    }
}