package com.uwaisalqadri.mangaku.data.mapper.entity

import com.uwaisalqadri.mangaku.data.mapper.BaseMapper
import com.uwaisalqadri.mangaku.data.souce.local.entity.PosterImageObject
import com.uwaisalqadri.mangaku.domain.model.PosterImage

class PosterImageObjectMapper: BaseMapper<PosterImageObject, PosterImage> {
    override fun mapToDomain(model: PosterImageObject): PosterImage {
        return PosterImage(
            model.large,
            model.medium,
            model.original,
            model.small,
            model.tiny
        )
    }

    override fun mapToModel(domain: PosterImage): PosterImageObject {
        val posterImageObject = PosterImageObject()
        posterImageObject.large = domain.large
        posterImageObject.medium = domain.medium
        posterImageObject.original = domain.original
        posterImageObject.small = domain.small
        posterImageObject.tiny = domain.tiny
        return posterImageObject
    }
}