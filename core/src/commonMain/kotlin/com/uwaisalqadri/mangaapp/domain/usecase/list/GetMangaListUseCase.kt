package com.uwaisalqadri.mangaapp.domain.usecase.list

import com.uwaisalqadri.mangaapp.data.souce.remote.response.Manga
import com.uwaisalqadri.mangaapp.domain.repository.MangaRepository
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