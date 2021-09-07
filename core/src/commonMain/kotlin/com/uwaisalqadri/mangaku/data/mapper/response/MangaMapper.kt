package com.uwaisalqadri.mangaku.data.mapper.response

import com.uwaisalqadri.mangaku.data.mapper.BaseMapper
import com.uwaisalqadri.mangaku.data.souce.remote.response.MangaItem
import com.uwaisalqadri.mangaku.domain.model.Manga

class MangaMapper(
    private val attributesMapper: AttributesMapper
): BaseMapper<MangaItem, Manga> {
    override fun mapToDomain(model: MangaItem): Manga {
        return Manga(
            attributesMapper.mapToDomain(model.attributes),
            model.id,
            model.type
        )
    }

    override fun mapToModel(domain: Manga): MangaItem {
        return MangaItem(
            attributesMapper.mapToModel(domain.attributes),
            domain.id,
            domain.type
        )
    }
}