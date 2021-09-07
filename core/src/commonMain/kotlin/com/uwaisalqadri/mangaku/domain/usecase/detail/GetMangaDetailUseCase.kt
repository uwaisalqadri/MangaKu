package com.uwaisalqadri.mangaku.domain.usecase.detail

import com.uwaisalqadri.mangaku.domain.model.Manga
import com.uwaisalqadri.mangaku.domain.repository.MangaRepository
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