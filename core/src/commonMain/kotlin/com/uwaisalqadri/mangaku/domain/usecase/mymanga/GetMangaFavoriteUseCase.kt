package com.uwaisalqadri.mangaku.domain.usecase.mymanga

import com.uwaisalqadri.mangaku.domain.model.Manga
import com.uwaisalqadri.mangaku.domain.repository.MangaRepository
import kotlinx.coroutines.flow.Flow

interface GetMangaFavoriteUseCase {
    suspend fun get(): Flow<List<Manga>>
    suspend fun getById(mangaId: String): Flow<List<Manga>>
    fun add(manga: Manga)
    fun delete(mangaId: String)
}

class GetMangaFavoriteInteractor(
    private val repository: MangaRepository
): GetMangaFavoriteUseCase {

    override suspend fun get(): Flow<List<Manga>> {
        return repository.getFavoriteManga()
    }

    override suspend fun getById(mangaId: String): Flow<List<Manga>> {
        return repository.getFavoriteMangaById(mangaId)
    }

    override fun add(manga: Manga) {
        repository.addMangaFavorite(manga)
    }

    override fun delete(mangaId: String) {
        repository.removeMangaFavorite(mangaId)
    }
}