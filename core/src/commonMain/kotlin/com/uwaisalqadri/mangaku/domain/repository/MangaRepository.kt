package com.uwaisalqadri.mangaku.domain.repository

import com.uwaisalqadri.mangaku.data.souce.local.entity.MangaObject
import com.uwaisalqadri.mangaku.data.souce.remote.response.MangaDetailResponse
import com.uwaisalqadri.mangaku.data.souce.remote.response.MangaItem
import com.uwaisalqadri.mangaku.data.souce.remote.response.MangaResponse

interface MangaRepository {
    suspend fun getManga(): List<MangaItem>
    suspend fun getTrendingManga(): List<MangaItem>
    suspend fun getSearchManga(query: String): List<MangaItem>
    suspend fun getDetailManga(mangaId: String): MangaItem?

    suspend fun getFavoriteManga(): List<MangaObject>
    suspend fun getFavoriteMangaById(mangaId: String): List<MangaObject>
    fun addMangaFavorite(manga: MangaObject)
    fun deleteMangaFavorite(mangaId: String)
}