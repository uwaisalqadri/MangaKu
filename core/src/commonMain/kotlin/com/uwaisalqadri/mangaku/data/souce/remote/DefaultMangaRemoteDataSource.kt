package com.uwaisalqadri.mangaku.data.souce.remote

import com.uwaisalqadri.mangaku.data.souce.remote.response.MangaDetailResponse
import com.uwaisalqadri.mangaku.data.souce.remote.response.MangaResponse
import com.uwaisalqadri.mangaku.domain.model.Manga
import com.uwaisalqadri.mangaku.utils.Constants
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*

class DefaultMangaRemoteDataSource(private val ktor: HttpClient): MangaRemoteDataSource {

    override suspend fun fetchManga(): MangaResponse {
        return ktor.get(urlString = Constants.baseUrl + "/manga")
    }

    override suspend fun fetchTrendingManga(): MangaResponse {
        return ktor.get(urlString = Constants.baseUrl + "/trending/manga")
    }

    override suspend fun fetchSearchManga(query: String): MangaResponse {
        return ktor.get(urlString = Constants.baseUrl + "/manga") {
            parameter("filter[text]", query)
        }
    }

    override suspend fun fetchDetailManga(id: String): MangaDetailResponse {
        return ktor.get(urlString = Constants.baseUrl + "/manga/$id")
    }

}