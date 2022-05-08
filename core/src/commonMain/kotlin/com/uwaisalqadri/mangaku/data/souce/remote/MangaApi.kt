package com.uwaisalqadri.mangaku.data.souce.remote

import com.uwaisalqadri.mangaku.data.souce.remote.response.MangaDetailResponse
import com.uwaisalqadri.mangaku.data.souce.remote.response.MangaResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

class MangaApi(private val ktor: HttpClient): MangaApiClient {

    override suspend fun fetchManga(): MangaResponse {
        return ktor.get("manga").body()
    }

    override suspend fun fetchTrendingManga(): MangaResponse {
        return ktor.get("trending/manga").body()
    }

    override suspend fun fetchSearchManga(query: String): MangaResponse {
        return ktor.get("manga") {
            parameter("filter[text]", query)
        }.body()
    }

    override suspend fun fetchDetailManga(id: String): MangaDetailResponse {
        return ktor.get("manga/$id").body()
    }

}