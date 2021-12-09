package com.uwaisalqadri.mangaku.data.souce.remote

import com.uwaisalqadri.mangaku.data.souce.remote.response.MangaDetailResponse
import com.uwaisalqadri.mangaku.data.souce.remote.response.MangaResponse
import com.uwaisalqadri.mangaku.domain.model.Manga
import com.uwaisalqadri.mangaku.utils.Constants
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*

class MangaRemoteDataSource(private val ktor: HttpClient) {

    suspend fun fetchManga(): MangaResponse {
        return ktor.get(path = "${Constants.baseUrl}/manga")
    }

    suspend fun fetchTrendingManga(): MangaResponse {
        return ktor.get(path = "${Constants.baseUrl}/trending/manga")
    }

    suspend fun fetchSearchManga(query: String): MangaResponse {
        return ktor.get(path = "${Constants.baseUrl}/manga") {
            parameter("filter[text]", query)
        }
    }

    suspend fun fetchDetailManga(id: String): MangaDetailResponse {
        return ktor.get("${Constants.baseUrl}/manga/$id")
    }

}