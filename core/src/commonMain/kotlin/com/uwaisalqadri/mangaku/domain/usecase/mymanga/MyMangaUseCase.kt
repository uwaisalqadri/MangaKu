package com.uwaisalqadri.mangaku.domain.usecase.mymanga

import com.uwaisalqadri.mangaku.domain.model.Manga
import kotlinx.coroutines.flow.Flow

interface MyMangaUseCase {
    suspend fun getMyManga(): Flow<List<Manga>>
    suspend fun getMyMangaById(mangaId: String): Flow<List<Manga>>
    fun addManga(manga: Manga)
    fun deleteManga(mangaId: String)
}