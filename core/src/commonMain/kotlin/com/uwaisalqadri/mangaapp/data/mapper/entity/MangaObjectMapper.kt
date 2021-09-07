package com.uwaisalqadri.mangaapp.data.mapper.entity

import com.uwaisalqadri.mangaapp.data.mapper.BaseMapper
import com.uwaisalqadri.mangaapp.data.souce.local.entity.MangaObject
import com.uwaisalqadri.mangaapp.data.souce.remote.response.MangaItem
import com.uwaisalqadri.mangaapp.domain.model.Manga

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