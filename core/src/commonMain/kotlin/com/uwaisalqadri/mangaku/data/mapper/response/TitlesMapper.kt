package com.uwaisalqadri.mangaku.data.mapper.response

import com.uwaisalqadri.mangaku.data.mapper.BaseMapper
import com.uwaisalqadri.mangaku.data.souce.remote.response.TitlesItem
import com.uwaisalqadri.mangaku.domain.model.Titles

class TitlesMapper: BaseMapper<TitlesItem, Titles> {
    override fun mapToDomain(model: TitlesItem): Titles {
        return Titles(
            model.en ?: "",
            model.en_jp ?: "",
            model.en_us ?: "",
            model.ja_jp ?: ""
        )
    }

    override fun mapToModel(domain: Titles): TitlesItem {
        return TitlesItem(
            domain.en,
            domain.en_jp,
            domain.en_us,
            domain.ja_jp
        )
    }
}