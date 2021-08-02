package com.uwaisalqadri.mangaapp.domain.usecase.list

import com.uwaisalqadri.mangaapp.data.souce.remote.response.Manga
import com.uwaisalqadri.mangaapp.domain.repository.MangaRepository
import kotlinx.coroutines.flow.Flow

interface GetMangaTrendingUseCase {
    suspend fun execute(): Flow<List<Manga>>
}

class GetMangaTrendingInteractor(
    private val repository: MangaRepository
): GetMangaTrendingUseCase {

    override suspend fun execute(): Flow<List<Manga>> {
        return repository.fetchTrendingMangas()
    }

}