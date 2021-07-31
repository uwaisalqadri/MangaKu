package com.uwaisalqadri.mangaapp.data.souce.remote

import com.uwaisalqadri.mangaapp.data.souce.remote.response.MangaDetailResponse
import com.uwaisalqadri.mangaapp.data.souce.remote.response.MangaResponse
import com.uwaisalqadri.mangaapp.utils.Constants
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*
import org.koin.core.component.KoinComponent

class ApiService(
    private val client: HttpClient,
    private val baseUrl: String = Constants.BASE_URL
) {

    suspend fun fetchMangas() =
        client.get<MangaResponse>("$baseUrl/manga") {
            addHeader()
        }

    suspend fun fetchTrendingMangas() =
        client.get<MangaResponse>("$baseUrl/trending/manga") {
            addHeader()
        }

    suspend fun fetchSearchMangas(query: String) =
        client.get<MangaResponse>("$baseUrl/manga?filter[text]=$query") {
            addHeader()
        }

    suspend fun fetchDetailManga(id: String) =
        client.get<MangaDetailResponse>("$baseUrl/manga/$id") {
            addHeader()
        }


    private fun HttpRequestBuilder.addHeader() {
        headers {
            append(HttpHeaders.Accept, "application/vnd.api+json")
            append(HttpHeaders.ContentType, "application/vnd.api+json")
        }
    }
}