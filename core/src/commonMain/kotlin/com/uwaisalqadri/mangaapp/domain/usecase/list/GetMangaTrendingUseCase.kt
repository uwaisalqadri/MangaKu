package com.uwaisalqadri.mangaapp.domain.usecase.list

import com.uwaisalqadri.mangaapp.domain.model.Manga
import com.uwaisalqadri.mangaapp.domain.repository.MangaRepository
import kotlinx.coroutines.flow.Flow

interface GetMangaTrendingUseCase {
    suspend operator fun invoke(): Flow<List<Manga>>
}

class GetMangaTrendingInteractor(
    private val repository: MangaRepository
): GetMangaTrendingUseCase {

    override suspend fun invoke(): Flow<List<Manga>> {
        return repository.fetchTrendingMangas()
    }

}