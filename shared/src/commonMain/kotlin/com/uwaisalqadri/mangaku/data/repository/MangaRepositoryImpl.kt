package com.uwaisalqadri.mangaku.data.repository

import com.uwaisalqadri.mangaku.data.mapper.map
import com.uwaisalqadri.mangaku.data.source.db.MangaDao
import com.uwaisalqadri.mangaku.data.source.api.KitsuApi
import com.uwaisalqadri.mangaku.data.source.api.NetworkService
import com.uwaisalqadri.mangaku.data.source.api.response.MangaDetailResponse
import com.uwaisalqadri.mangaku.data.source.api.response.MangaResponse
import com.uwaisalqadri.mangaku.domain.base.model.Manga
import com.uwaisalqadri.mangaku.domain.repository.MangaRepository

class MangaRepositoryImpl(
    private val network: NetworkService,
    private val database: MangaDao
): MangaRepository {

    override suspend fun getManga(): List<Manga> {
        val response: MangaResponse = network.connect(KitsuApi.GetList)
        return response.data.map()
    }

    override suspend fun getTrendingManga(): List<Manga> {
        val response: MangaResponse = network.connect(KitsuApi.Trending)
        return response.data.map()
    }

    override suspend fun getSearchManga(query: String): List<Manga> {
        val response: MangaResponse = network.connect(KitsuApi.Search(query))
        return response.data.map()
    }

    override suspend fun getDetailManga(mangaId: String): Manga {
        val response: MangaDetailResponse = network.connect(KitsuApi.Detail(mangaId))
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
