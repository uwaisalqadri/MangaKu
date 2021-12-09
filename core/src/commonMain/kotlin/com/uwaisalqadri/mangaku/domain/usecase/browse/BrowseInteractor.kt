package com.uwaisalqadri.mangaku.domain.usecase.browse

import com.uwaisalqadri.mangaku.domain.model.Manga
import com.uwaisalqadri.mangaku.domain.repository.MangaRepository
import kotlinx.coroutines.flow.Flow

class BrowseInteractor(private val repository: MangaRepository): BrowseUseCase {

    override suspend fun getManga(): Flow<List<Manga>> {
        return repository.getManga()
    }

    override suspend fun getTrendingManga(): Flow<List<Manga>> {
        return repository.getTrendingManga()
    }
}