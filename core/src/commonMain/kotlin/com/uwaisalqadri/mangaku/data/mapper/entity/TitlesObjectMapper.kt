package com.uwaisalqadri.mangaku.data.mapper.entity

import com.uwaisalqadri.mangaku.data.mapper.BaseMapper
import com.uwaisalqadri.mangaku.data.souce.local.entity.TitlesObject
import com.uwaisalqadri.mangaku.domain.model.Titles

class TitlesObjectMapper: BaseMapper<TitlesObject, Titles> {
    override fun mapToDomain(model: TitlesObject): Titles {
        return Titles(
            model.en,
            model.en_jp,
            model.en_us,
            model.ja_jp
        )
    }

    override fun mapToModel(domain: Titles): TitlesObject {
        val titlesObject = TitlesObject()
        titlesObject.en = domain.en
        titlesObject.en_jp = domain.en_jp
        titlesObject.en_us = domain.en_us
        titlesObject.ja_jp = domain.ja_jp
        return titlesObject
    }
}