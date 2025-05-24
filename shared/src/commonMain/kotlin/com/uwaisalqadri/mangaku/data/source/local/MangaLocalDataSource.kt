package com.uwaisalqadri.mangaku.data.source.local

import com.uwaisalqadri.mangaku.db.MangaEntity

interface MangaLocalDataSource {
    fun getAllManga(): List<MangaEntity>
    fun getMangaById(mangaId: String): List<MangaEntity>
    suspend fun addManga(manga: MangaEntity)
    suspend fun deleteManga(mangaId: String)
    suspend fun clearAllManga()
}