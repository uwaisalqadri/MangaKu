package com.uwaisalqadri.mangaku.data.mapper.response

import com.uwaisalqadri.mangaku.data.mapper.BaseMapper
import com.uwaisalqadri.mangaku.data.souce.remote.response.CoverImageItem
import com.uwaisalqadri.mangaku.domain.model.CoverImage

class CoverImageMapper: BaseMapper<CoverImageItem, CoverImage> {
    override fun mapToDomain(model: CoverImageItem): CoverImage {
        return CoverImage(
            model.large,
            model.medium,
            model.original,
            model.small,
            model.tiny
        )
    }

    override fun mapToModel(domain: CoverImage): CoverImageItem {
        return CoverImageItem(
            domain.large,
            domain.medium,
            domain.original,
            domain.small,
            domain.tiny
        )
    }
}