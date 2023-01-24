package com.uwaisalqadri.mangaku.domain.usecase.mymanga

import com.rickclephas.kmp.nativecoroutines.NativeCoroutines
import com.uwaisalqadri.mangaku.domain.model.Manga
import kotlinx.coroutines.flow.Flow

interface MyMangaUseCase {
    @NativeCoroutines
    suspend fun getMyManga(): Flow<List<Manga>>
    @NativeCoroutines
    suspend fun getMyMangaById(mangaId: String): Flow<List<Manga>>
    fun addManga(manga: Manga)
    fun deleteManga(mangaId: String)
}