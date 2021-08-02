package com.uwaisalqadri.mangaapp.domain.usecase.detail

import com.uwaisalqadri.mangaapp.data.souce.remote.response.Manga
import com.uwaisalqadri.mangaapp.domain.repository.MangaRepository
import kotlinx.coroutines.flow.Flow

interface GetMangaDetailUseCase {
    suspend fun execute(id: String): Flow<Manga?>
}

class GetMangaDetailInteractor(
    private val repository: MangaRepository
): GetMangaDetailUseCase {

    override suspend fun execute(id: String): Flow<Manga?> {
        return repository.fetchDetailManga(id)
    }
}