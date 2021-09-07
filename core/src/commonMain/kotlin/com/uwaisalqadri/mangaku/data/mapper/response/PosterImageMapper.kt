package com.uwaisalqadri.mangaku.data.mapper.response

import com.uwaisalqadri.mangaku.data.mapper.BaseMapper
import com.uwaisalqadri.mangaku.data.souce.remote.response.PosterImageItem
import com.uwaisalqadri.mangaku.domain.model.PosterImage

class PosterImageMapper: BaseMapper<PosterImageItem, PosterImage> {
    override fun mapToDomain(model: PosterImageItem): PosterImage {
        return PosterImage(
            model.large ?: "",
            model.medium ?: "",
            model.original ?: "",
            model.small ?: "",
            model.tiny ?: ""
        )
    }

    override fun mapToModel(domain: PosterImage): PosterImageItem {
        return PosterImageItem(
            domain.large,
            domain.medium,
            domain.original,
            domain.small,
            domain.tiny
        )
    }
}