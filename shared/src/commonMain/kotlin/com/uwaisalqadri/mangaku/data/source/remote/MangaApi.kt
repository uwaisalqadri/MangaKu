package com.uwaisalqadri.mangaku.data.source.remote

import com.uwaisalqadri.mangaku.data.source.remote.response.MangaDetailResponse
import com.uwaisalqadri.mangaku.data.source.remote.response.MangaResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

class MangaApi(private val apiClient: HttpClient): MangaApiClient {

    override suspend fun fetchManga(): MangaResponse {
        return apiClient.get("api/edge/manga").body()
    }

    override suspend fun fetchTrendingManga(): MangaResponse {
        return apiClient.get("api/edge/trending/manga").body()
    }

    override suspend fun fetchSearchManga(query: String): MangaResponse {
        return apiClient.get("api/edge/manga") {
            parameter("filter[text]", query)
        }.body()
    }

    override suspend fun fetchDetailManga(id: String): MangaDetailResponse {
        return apiClient.get("api/edge/manga/$id").body()
    }

}