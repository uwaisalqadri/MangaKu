package com.uwaisalqadri.mangaku.data.repository

import com.uwaisalqadri.mangaku.data.souce.local.MangaLocalDataSource
import com.uwaisalqadri.mangaku.data.souce.local.entity.MangaObject
import com.uwaisalqadri.mangaku.data.souce.remote.MangaApiClient
import com.uwaisalqadri.mangaku.data.souce.remote.response.MangaItem
import com.uwaisalqadri.mangaku.domain.repository.MangaRepository

class MangaDataStore(
    private val mangaApiClient: MangaApiClient,
    private val mangaLocalDataSource: MangaLocalDataSource
): MangaRepository {

    override suspend fun getManga(): List<MangaItem> {
        val response = mangaApiClient.fetchManga()
        return response.data
    }

    override suspend fun getTrendingManga(): List<MangaItem> {
        val response = mangaApiClient.fetchTrendingManga()
        return response.data
    }

    override suspend fun getSearchManga(query: String): List<MangaItem> {
        val response = mangaApiClient.fetchSearchManga(query = query)
        return response.data
    }

    override suspend fun getDetailManga(mangaId: String): MangaItem? {
        val response = mangaApiClient.fetchDetailManga(id = mangaId)
        return response.data
    }

    override suspend fun getFavoriteManga(): List<MangaObject> {
        return mangaLocalDataSource.getAllManga()
    }

    override suspend fun getFavoriteMangaById(mangaId: String): List<MangaObject> {
        return mangaLocalDataSource.getMangaById(mangaId = mangaId)
    }

    override fun addMangaFavorite(manga: MangaObject) {
        mangaLocalDataSource.addManga(manga)
    }

    override fun deleteMangaFavorite(mangaId: String) {
        mangaLocalDataSource.deleteManga(mangaId)
    }

}