package com.uwaisalqadri.mangaku.data.souce.remote

import com.uwaisalqadri.mangaku.data.souce.remote.response.MangaDetailResponse
import com.uwaisalqadri.mangaku.data.souce.remote.response.MangaResponse
import com.uwaisalqadri.mangaku.utils.Constants
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*

class RemoteDataSource(private val client: HttpClient) {

    suspend fun fetchMangas() =
        client.get<MangaResponse>("${Constants.BASE_URL}/manga") {
            createHeader()
        }

    suspend fun fetchTrendingMangas() =
        client.get<MangaResponse>("${Constants.BASE_URL}/trending/manga") {
            createHeader()
        }

    suspend fun fetchSearchMangas(query: String) =
        client.get<MangaResponse>("${Constants.BASE_URL}/manga") {
            createHeader()
            parameter("filter[text]", query)
        }

    suspend fun fetchDetailManga(id: String) =
        client.get<MangaDetailResponse>("${Constants.BASE_URL}/manga/$id") {
            createHeader()
        }


    private fun HttpRequestBuilder.createHeader() {
        headers {
            append(HttpHeaders.Accept, "application/vnd.api+json")
            append(HttpHeaders.ContentType, "application/vnd.api+json")
        }
    }
}