package com.uwaisalqadri.mangaku.data.mapper.response

import com.uwaisalqadri.mangaku.data.mapper.BaseMapper
import com.uwaisalqadri.mangaku.data.souce.remote.response.MangaItem
import com.uwaisalqadri.mangaku.domain.model.Manga

class MangaMapper(
    private val attributesMapper: AttributesMapper
): BaseMapper<MangaItem, Manga> {
    override fun mapToDomain(model: MangaItem): Manga {
        return Manga(
            model.attributes?.let { attributesMapper.mapToDomain(it) },
            model.id,
            model.type
        )
    }

    override fun mapToModel(domain: Manga): MangaItem {
        return MangaItem(
            domain.attributes?.let { attributesMapper.mapToModel(it) },
            domain.id,
            domain.type
        )
    }
}