package com.uwaisalqadri.mangaku.data.mapper.entity

import com.uwaisalqadri.mangaku.data.mapper.BaseMapper
import com.uwaisalqadri.mangaku.data.souce.local.entity.MangaObject
import com.uwaisalqadri.mangaku.domain.model.Manga

class MangaObjectMapper(
    private val attributesObjectMapper: AttributesObjectMapper
): BaseMapper<MangaObject, Manga> {

    override fun mapToDomain(model: MangaObject): Manga {
        return Manga(
            model.attributes?.let { attributesObjectMapper.mapToDomain(it) },
            model.id,
            model.type
        )
    }

    override fun mapToModel(domain: Manga): MangaObject {
        val mangaObject = MangaObject()
        mangaObject.attributes = domain.attributes?.let { attributesObjectMapper.mapToModel(it) }
        mangaObject.id = domain.id
        mangaObject.type = domain.type
        return mangaObject
    }
}