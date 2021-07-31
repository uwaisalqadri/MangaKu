package com.uwaisalqadri.mangaapp.domain.repository

import com.uwaisalqadri.mangaapp.data.souce.remote.response.Manga
import kotlinx.coroutines.flow.Flow

interface MangaRepository {
    suspend fun fetchMangas(): Flow<List<Manga>>
    suspend fun fetchTrendingMangas(): Flow<List<Manga>>
    suspend fun fetchSearchMangas(query: String): Flow<List<Manga>>
    suspend fun fetchDetailManga(id: String): Flow<Manga?>
}