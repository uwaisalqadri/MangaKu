package com.uwaisalqadri.mangaapp.domain.repository

import com.uwaisalqadri.mangaapp.data.souce.remote.response.Manga
import com.uwaisalqadri.mangaapp.data.souce.remote.response.MangaResponse
import com.uwaisalqadri.mangaapp.utils.Resource
import io.ktor.client.request.*
import kotlinx.coroutines.flow.Flow

interface MangaRepository {
    suspend fun fetchMangas(): Flow<Resource<List<Manga>>>
    suspend fun fetchSearchMangas(query: String): Flow<Resource<List<Manga>>>
    suspend fun fetchDetailManga(id: String): Flow<Resource<Manga?>>
}