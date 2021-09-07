package com.uwaisalqadri.mangaku.domain.usecase.search

import com.uwaisalqadri.mangaku.domain.model.Manga
import com.uwaisalqadri.mangaku.domain.repository.MangaRepository
import kotlinx.coroutines.flow.Flow

interface GetMangaSearchUseCase {
    suspend operator fun invoke(query: String): Flow<List<Manga>>
}

class GetMangaSearchInteractor(
    private val repository: MangaRepository
): GetMangaSearchUseCase {

    override suspend fun invoke(query: String): Flow<List<Manga>> {
        return repository.fetchSearchMangas(query)
    }
}