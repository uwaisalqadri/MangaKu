package com.uwaisalqadri.mangaku.domain.usecase.browse

import com.uwaisalqadri.mangaku.domain.base.BaseInteractor
import com.uwaisalqadri.mangaku.domain.mapper.map
import com.uwaisalqadri.mangaku.domain.model.Manga
import com.uwaisalqadri.mangaku.domain.repository.MangaRepository
import kotlinx.coroutines.flow.Flow

class BrowseInteractor(private val repository: MangaRepository): BrowseUseCase, BaseInteractor() {

    override suspend fun getManga(): Flow<List<Manga>> {
        return execute {
            repository.getManga().map()
        }
    }

    override suspend fun getTrendingManga(): Flow<List<Manga>> {
        return execute {
            repository.getTrendingManga().map()
        }
    }
}