package com.uwaisalqadri.mangaapp.data.mapper.response

import com.uwaisalqadri.mangaapp.data.mapper.BaseMapper
import com.uwaisalqadri.mangaapp.data.souce.remote.response.CoverImageItem
import com.uwaisalqadri.mangaapp.domain.model.CoverImage

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