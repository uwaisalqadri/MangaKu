package com.uwaisalqadri.mangaku.data.souce.local

import com.uwaisalqadri.mangaku.data.souce.local.entity.MangaObject

interface MangaPersistence {
    fun getAllManga(): List<MangaObject>
    fun getMangaById(mangaId: String): List<MangaObject>
    fun addManga(manga: MangaObject)
    fun deleteManga(mangaId: String)
    fun clearAllManga()
    fun close()
}