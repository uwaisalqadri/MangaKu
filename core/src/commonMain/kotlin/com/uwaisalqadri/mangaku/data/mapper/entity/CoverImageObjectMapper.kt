package com.uwaisalqadri.mangaku.data.mapper.entity

import com.uwaisalqadri.mangaku.data.mapper.BaseMapper
import com.uwaisalqadri.mangaku.data.souce.local.entity.CoverImageObject
import com.uwaisalqadri.mangaku.domain.model.CoverImage

class CoverImageObjectMapper: BaseMapper<CoverImageObject, CoverImage> {
    override fun mapToDomain(model: CoverImageObject): CoverImage {
        return CoverImage(
            model.large,
            model.medium,
            model.original,
            model.small,
            model.tiny
        )
    }

    override fun mapToModel(domain: CoverImage): CoverImageObject {
        val coverImageObject = CoverImageObject()
        coverImageObject.large = domain.large ?: ""
        coverImageObject.medium = domain.medium ?: ""
        coverImageObject.original = domain.original ?: ""
        coverImageObject.small = domain.small ?: ""
        coverImageObject.tiny = domain.tiny ?: ""
        return coverImageObject
    }
}