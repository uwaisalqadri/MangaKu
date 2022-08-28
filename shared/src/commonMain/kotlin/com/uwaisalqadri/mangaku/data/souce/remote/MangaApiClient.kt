package com.uwaisalqadri.mangaku.data.souce.remote

import com.uwaisalqadri.mangaku.data.souce.remote.response.MangaDetailResponse
import com.uwaisalqadri.mangaku.data.souce.remote.response.MangaResponse

interface MangaApiClient {
    suspend fun fetchManga(): MangaResponse
    suspend fun fetchTrendingManga(): MangaResponse
    suspend fun fetchSearchManga(query: String): MangaResponse
    suspend fun fetchDetailManga(id: String): MangaDetailResponse
}