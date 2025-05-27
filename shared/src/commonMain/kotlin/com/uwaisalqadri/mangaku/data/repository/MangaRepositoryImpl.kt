package com.uwaisalqadri.mangaku.data.repository

import com.uwaisalqadri.mangaku.data.mapper.map
import com.uwaisalqadri.mangaku.data.source.local.MangaDao
import com.uwaisalqadri.mangaku.data.source.remote.MangaApi
import com.uwaisalqadri.mangaku.data.source.remote.NetworkService
import com.uwaisalqadri.mangaku.data.source.remote.response.MangaDetailResponse
import com.uwaisalqadri.mangaku.data.source.remote.response.MangaResponse
import com.uwaisalqadri.mangaku.domain.model.Manga
import com.uwaisalqadri.mangaku.domain.repository.MangaRepository

class MangaRepositoryImpl(
    private val network: NetworkService,
    private val database: MangaDao
): MangaRepository {

    override suspend fun getManga(): List<Manga> {
        val response: MangaResponse = network.connect(MangaApi.GetList)
        return response.data.map()
    }

    override suspend fun getTrendingManga(): List<Manga> {
        val response: MangaResponse = network.connect(MangaApi.Trending)
        return response.data.map()
    }

    override suspend fun getSearchManga(query: String): List<Manga> {
        val response: MangaResponse = network.connect(MangaApi.Search(query))
        return response.data.map()
    }

    override suspend fun getDetailManga(mangaId: String): Manga {
        val response: MangaDetailResponse = network.connect(MangaApi.Detail(mangaId))
        return response.data.map()
    }

    override suspend fun getFavoriteManga(): List<Manga> {
        return database.getAllManga().map()
    }

    override suspend fun getFavoriteMangaById(mangaId: String): List<Manga> {
        return database.getMangaById(mangaId = mangaId).map()
    }

    override suspend fun addMangaFavorite(manga: Manga) {
        database.addManga(manga.map())
    }

    override suspend fun deleteMangaFavorite(mangaId: String) {
        database.deleteManga(mangaId)
    }
}