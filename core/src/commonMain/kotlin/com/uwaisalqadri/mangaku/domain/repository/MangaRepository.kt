package com.uwaisalqadri.mangaku.domain.repository

import com.uwaisalqadri.mangaku.domain.model.Manga
import kotlinx.coroutines.flow.Flow

interface MangaRepository {
    suspend fun fetchMangas(): Flow<List<Manga>>
    suspend fun fetchTrendingMangas(): Flow<List<Manga>>
    suspend fun fetchSearchMangas(query: String): Flow<List<Manga>>
    suspend fun fetchDetailManga(id: String): Flow<Manga?>

    suspend fun getFavoriteManga(): Flow<List<Manga>>
    fun addMangaFavorite(manga: Manga)
    fun removeMangaFavorite(mangaId: Int)
}