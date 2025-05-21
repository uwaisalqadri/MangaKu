package com.uwaisalqadri.mangaku.domain.repository

import com.uwaisalqadri.mangaku.data.source.local.entity.MangaObject
import com.uwaisalqadri.mangaku.data.source.remote.response.MangaItem

interface MangaRepository {
    suspend fun getManga(): List<MangaItem>
    suspend fun getTrendingManga(): List<MangaItem>
    suspend fun getSearchManga(query: String): List<MangaItem>
    suspend fun getDetailManga(mangaId: String): MangaItem?

    suspend fun getFavoriteManga(): List<MangaObject>
    suspend fun getFavoriteMangaById(mangaId: String): List<MangaObject>
    fun addMangaFavorite(manga: MangaObject)
    fun deleteMangaFavorite(mangaId: String)
    fun closePersistence()
}