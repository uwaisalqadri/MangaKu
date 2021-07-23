package com.uwaisalqadri.mangaapp.data.souce.remote

import com.uwaisalqadri.mangaapp.data.souce.remote.response.MangaDetailResponse
import com.uwaisalqadri.mangaapp.data.souce.remote.response.MangaResponse
import com.uwaisalqadri.mangaapp.utils.Constants
import io.ktor.client.*
import io.ktor.client.request.*
import org.koin.core.component.KoinComponent

class ApiService(
    private val client: HttpClient,
    private val baseUrl: String = Constants.BASE_URL
): KoinComponent {

    suspend fun fetchMangas() =
        client.get<MangaResponse>("$baseUrl/manga")

    suspend fun fetchSearchMangas(query: String) =
        client.get<MangaResponse>("$baseUrl/manga?filter[text]=$query")

    suspend fun fetchDetailManga(id: String) =
        client.get<MangaDetailResponse>("$baseUrl/manga/$id")
}