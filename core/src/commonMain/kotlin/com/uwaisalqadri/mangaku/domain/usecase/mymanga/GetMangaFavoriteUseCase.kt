package com.uwaisalqadri.mangaku.domain.usecase.mymanga

import com.uwaisalqadri.mangaku.domain.model.Manga
import com.uwaisalqadri.mangaku.domain.repository.MangaRepository
import kotlinx.coroutines.flow.Flow

interface GetMangaFavoriteUseCase {
    suspend operator fun invoke(): Flow<List<Manga>>
}

class GetMangaFavoriteInteractor(
    private val repository: MangaRepository
): GetMangaFavoriteUseCase {

    override suspend fun invoke(): Flow<List<Manga>> {
        return repository.getFavoriteManga()
    }
}