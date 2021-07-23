package com.uwaisalqadri.mangaapp.data.souce.remote

import com.uwaisalqadri.mangaapp.data.souce.remote.response.MangaResponse
import com.uwaisalqadri.mangaapp.utils.Constants
import io.ktor.client.*
import io.ktor.client.request.*
import org.koin.core.component.KoinComponent

class ApiService(
    private val client: HttpClient,
    val baseUrl: String = Constants.BASE_URL
): KoinComponent {
    suspend fun fetchMangas() = client.get<MangaResponse>("$baseUrl/manga")
}