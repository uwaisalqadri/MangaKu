package com.uwaisalqadri.mangaku.domain.usecase.mymanga

import com.uwaisalqadri.mangaku.domain.model.Manga
import com.uwaisalqadri.mangaku.domain.repository.MangaRepository
import kotlinx.coroutines.flow.Flow

class MyMangaInteractor(private val repository: MangaRepository): MyMangaUseCase {

    override suspend fun getMyManga(): Flow<List<Manga>> {
        return repository.getFavoriteManga()
    }

    override suspend fun getMyMangaById(mangaId: String): Flow<List<Manga>> {
        return repository.getFavoriteMangaById(mangaId = mangaId)
    }

    override fun addManga(manga: Manga) {
        repository.addMangaFavorite(manga = manga)
    }

    override fun deleteManga(mangaId: String) {
        repository.deleteMangaFavorite(mangaId = mangaId)
    }
}