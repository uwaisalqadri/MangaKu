package com.uwaisalqadri.mangaapp.data.mapper.response

import com.uwaisalqadri.mangaapp.data.mapper.BaseMapper
import com.uwaisalqadri.mangaapp.data.mapper.entity.AttributesObjectMapper
import com.uwaisalqadri.mangaapp.data.souce.remote.response.MangaItem
import com.uwaisalqadri.mangaapp.domain.model.Manga

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