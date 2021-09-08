package com.uwaisalqadri.mangaku.domain.usecase.mymanga

import com.uwaisalqadri.mangaku.domain.model.Manga
import com.uwaisalqadri.mangaku.domain.repository.MangaRepository

interface CreateMangaFavoriteUseCase {
    fun add(manga: Manga)
    fun delete(mangaId: Int)
}

class CreateMangaFavoriteInteractor(
    private val repository: MangaRepository
): CreateMangaFavoriteUseCase {

    override fun add(manga: Manga) {
        repository.addMangaFavorite(manga)
    }

    override fun delete(mangaId: Int) {
        repository.removeMangaFavorite(mangaId)
    }

}