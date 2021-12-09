package com.uwaisalqadri.mangaku.domain.repository

import com.uwaisalqadri.mangaku.domain.model.Manga
import kotlinx.coroutines.flow.Flow

interface MangaRepository {
    suspend fun getManga(): Flow<List<Manga>>
    suspend fun getTrendingManga(): Flow<List<Manga>>
    suspend fun getSearchManga(query: String): Flow<List<Manga>>
    suspend fun getDetailManga(id: String): Flow<Manga?>

    suspend fun getFavoriteManga(): Flow<List<Manga>>
    suspend fun getFavoriteMangaById(mangaId: String): Flow<List<Manga>>
    fun addMangaFavorite(manga: Manga)
    fun deleteMangaFavorite(mangaId: String)
}