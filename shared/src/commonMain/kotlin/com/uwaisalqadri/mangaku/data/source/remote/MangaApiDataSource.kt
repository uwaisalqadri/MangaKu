package com.uwaisalqadri.mangaku.data.source.remote

import com.uwaisalqadri.mangaku.data.source.remote.response.MangaDetailResponse
import com.uwaisalqadri.mangaku.data.source.remote.response.MangaResponse

interface MangaApiDataSource {
    suspend fun fetchManga(): MangaResponse
    suspend fun fetchTrendingManga(): MangaResponse
    suspend fun fetchSearchManga(query: String): MangaResponse
    suspend fun fetchDetailManga(id: String): MangaDetailResponse
}