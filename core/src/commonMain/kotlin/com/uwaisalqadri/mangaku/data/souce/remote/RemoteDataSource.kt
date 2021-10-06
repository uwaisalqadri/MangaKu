package com.uwaisalqadri.mangaku.data.souce.remote

import com.uwaisalqadri.mangaku.data.souce.remote.response.MangaDetailResponse
import com.uwaisalqadri.mangaku.data.souce.remote.response.MangaResponse
import com.uwaisalqadri.mangaku.utils.Constants
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*

class RemoteDataSource(private val ktor: HttpClient) {

    suspend fun fetchMangas() =
        ktor.get<MangaResponse>("${Constants.baseUrl}/manga")

    suspend fun fetchTrendingMangas() =
        ktor.get<MangaResponse>("${Constants.baseUrl}/trending/manga")

    suspend fun fetchSearchMangas(query: String) =
        ktor.get<MangaResponse>("${Constants.baseUrl}/manga") {
            parameter("filter[text]", query)
        }

    suspend fun fetchDetailManga(id: String) =
        ktor.get<MangaDetailResponse>("${Constants.baseUrl}/manga/$id")

}