package com.uwaisalqadri.mangaku.data.souce.remote

import com.uwaisalqadri.mangaku.data.souce.remote.response.MangaDetailResponse
import com.uwaisalqadri.mangaku.data.souce.remote.response.MangaResponse
import com.uwaisalqadri.mangaku.utils.Constants
import io.ktor.client.*
import io.ktor.client.request.*

class MangaApi(private val ktor: HttpClient): MangaApiClient {

    override suspend fun fetchManga(): MangaResponse {
        return ktor.get(path = "/manga")
    }

    override suspend fun fetchTrendingManga(): MangaResponse {
        return ktor.get(path = "/trending/manga")
    }

    override suspend fun fetchSearchManga(query: String): MangaResponse {
        return ktor.get(path = "/manga") {
            parameter("filter[text]", query)
        }
    }

    override suspend fun fetchDetailManga(id: String): MangaDetailResponse {
        return ktor.get(path = "/manga/$id")
    }

}