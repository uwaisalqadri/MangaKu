package com.uwaisalqadri.mangaku.data.mapper.entity

import com.uwaisalqadri.mangaku.data.mapper.BaseMapper
import com.uwaisalqadri.mangaku.data.souce.local.entity.MangaObject
import com.uwaisalqadri.mangaku.domain.model.Manga

class MangaObjectMapper(
    private val attributesObjectMapper: AttributesObjectMapper
): BaseMapper<MangaObject, Manga> {

    override fun mapToDomain(model: MangaObject): Manga {
        return Manga(
            attributesObjectMapper.mapToDomain(model.attributes!!),
            model.mangaId,
            model.type
        )
    }

    override fun mapToModel(domain: Manga): MangaObject {
        val mangaObject = MangaObject()
        attributesObjectMapper.mapToModel(domain.attributes)
        mangaObject.mangaId = domain.id
        mangaObject.type = domain.type
        return mangaObject
    }
}