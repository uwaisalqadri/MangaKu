package com.uwaisalqadri.mangaku.domain.usecase.browse

import com.uwaisalqadri.mangaku.domain.model.Manga
import com.uwaisalqadri.mangaku.domain.repository.MangaRepository
import kotlinx.coroutines.flow.Flow

interface GetMangaListUseCase {
    suspend operator fun invoke(): Flow<List<Manga>>
}

class GetMangaListInteractor(
    private val repository: MangaRepository
): GetMangaListUseCase {

    override suspend fun invoke(): Flow<List<Manga>> {
        return repository.fetchMangas()
    }
}