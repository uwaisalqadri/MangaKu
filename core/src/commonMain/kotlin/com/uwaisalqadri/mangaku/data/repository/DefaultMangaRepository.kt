package com.uwaisalqadri.mangaku.data.repository

import com.uwaisalqadri.mangaku.data.souce.local.MangaLocalDataSource
import com.uwaisalqadri.mangaku.data.souce.local.entity.MangaObject
import com.uwaisalqadri.mangaku.data.souce.remote.MangaRemoteDataSource
import com.uwaisalqadri.mangaku.data.souce.remote.response.MangaDetailResponse
import com.uwaisalqadri.mangaku.data.souce.remote.response.MangaItem
import com.uwaisalqadri.mangaku.data.souce.remote.response.MangaResponse
import com.uwaisalqadri.mangaku.domain.repository.MangaRepository

class DefaultMangaRepository(
    private val mangaRemoteDataSource: MangaRemoteDataSource,
    private val mangaLocalDataSource: MangaLocalDataSource
): MangaRepository {

    override suspend fun getManga(): List<MangaItem> {
        val response = mangaRemoteDataSource.fetchManga()
        return response.data
    }

    override suspend fun getTrendingManga(): List<MangaItem> {
        val response = mangaRemoteDataSource.fetchTrendingManga()
        return response.data
    }

    override suspend fun getSearchManga(query: String): List<MangaItem> {
        val response = mangaRemoteDataSource.fetchSearchManga(query = query)
        return response.data
    }

    override suspend fun getDetailManga(mangaId: String): MangaItem? {
        val response = mangaRemoteDataSource.fetchDetailManga(id = mangaId)
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