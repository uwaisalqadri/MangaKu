package com.uwaisalqadri.mangaku.data.repository

import com.uwaisalqadri.mangaku.data.source.local.MangaLocalDataSource
import com.uwaisalqadri.mangaku.data.source.local.entity.MangaObject
import com.uwaisalqadri.mangaku.data.source.remote.MangaApiDataSource
import com.uwaisalqadri.mangaku.data.source.remote.response.MangaItemResponse
import com.uwaisalqadri.mangaku.domain.repository.MangaRepository

class MangaRepositoryImpl(
    private val mangaApiDataSource: MangaApiDataSource,
    private val mangaLocalDataSource: MangaLocalDataSource
): MangaRepository {

    override suspend fun getManga(): List<MangaItemResponse> {
        val response = mangaApiDataSource.fetchManga()
        return response.data
    }

    override suspend fun getTrendingManga(): List<MangaItemResponse> {
        val response = mangaApiDataSource.fetchTrendingManga()
        return response.data
    }

    override suspend fun getSearchManga(query: String): List<MangaItemResponse> {
        val response = mangaApiDataSource.fetchSearchManga(query = query)
        return response.data
    }

    override suspend fun getDetailManga(mangaId: String): MangaItemResponse? {
        val response = mangaApiDataSource.fetchDetailManga(id = mangaId)
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

    override fun closePersistence() {
        mangaLocalDataSource.close()
    }

}