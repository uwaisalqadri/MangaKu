package com.uwaisalqadri.mangaapp.domain.usecase.detail

import com.uwaisalqadri.mangaapp.domain.model.Manga
import com.uwaisalqadri.mangaapp.domain.repository.MangaRepository
import kotlinx.coroutines.flow.Flow

interface GetMangaDetailUseCase {
    suspend operator fun invoke(id: String): Flow<Manga?>
}

class GetMangaDetailInteractor(
    private val repository: MangaRepository
): GetMangaDetailUseCase {

    override suspend fun invoke(id: String): Flow<Manga?> {
        return repository.fetchDetailManga(id)
    }
}