package com.uwaisalqadri.mangaku.data.repository

import com.uwaisalqadri.mangaku.data.mapper.map
import com.uwaisalqadri.mangaku.data.source.local.MangaLocalDataSource
import com.uwaisalqadri.mangaku.data.source.remote.MangaApiDataSource
import com.uwaisalqadri.mangaku.domain.model.Manga
import com.uwaisalqadri.mangaku.domain.repository.MangaRepository

class MangaRepositoryImpl(
    private val mangaApiDataSource: MangaApiDataSource,
    private val mangaLocalDataSource: MangaLocalDataSource
): MangaRepository {

    override suspend fun getManga(): List<Manga> {
        val response = mangaApiDataSource.fetchManga()
        return response.data.map()
    }

    override suspend fun getTrendingManga(): List<Manga> {
        val response = mangaApiDataSource.fetchTrendingManga()
        return response.data.map()
    }

    override suspend fun getSearchManga(query: String): List<Manga> {
        val response = mangaApiDataSource.fetchSearchManga(query = query)
        return response.data.map()
    }

    override suspend fun getDetailManga(mangaId: String): Manga {
        val response = mangaApiDataSource.fetchDetailManga(id = mangaId)
        return response.data.map()
    }

    override suspend fun getFavoriteManga(): List<Manga> {
        return mangaLocalDataSource.getAllManga().map()
    }

    override suspend fun getFavoriteMangaById(mangaId: String): List<Manga> {
        return mangaLocalDataSource.getMangaById(mangaId = mangaId).map()
    }

    override suspend fun addMangaFavorite(manga: Manga) {
        mangaLocalDataSource.addManga(manga.map())
    }

    override suspend fun deleteMangaFavorite(mangaId: String) {
        mangaLocalDataSource.deleteManga(mangaId)
    }
}