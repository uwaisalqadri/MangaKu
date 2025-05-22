package com.uwaisalqadri.mangaku.domain.repository

import com.uwaisalqadri.mangaku.data.source.local.entity.MangaObject
import com.uwaisalqadri.mangaku.data.source.remote.response.MangaItemResponse

interface MangaRepository {
    suspend fun getManga(): List<MangaItemResponse>
    suspend fun getTrendingManga(): List<MangaItemResponse>
    suspend fun getSearchManga(query: String): List<MangaItemResponse>
    suspend fun getDetailManga(mangaId: String): MangaItemResponse?

    suspend fun getFavoriteManga(): List<MangaObject>
    suspend fun getFavoriteMangaById(mangaId: String): List<MangaObject>
    fun addMangaFavorite(manga: MangaObject)
    fun deleteMangaFavorite(mangaId: String)
    fun closePersistence()
}