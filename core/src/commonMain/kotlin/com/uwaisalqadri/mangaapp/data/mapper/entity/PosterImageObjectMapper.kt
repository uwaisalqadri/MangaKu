package com.uwaisalqadri.mangaapp.data.mapper.entity

import com.uwaisalqadri.mangaapp.data.mapper.BaseMapper
import com.uwaisalqadri.mangaapp.data.souce.local.entity.PosterImageObject
import com.uwaisalqadri.mangaapp.data.souce.remote.response.PosterImageItem
import com.uwaisalqadri.mangaapp.domain.model.PosterImage

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