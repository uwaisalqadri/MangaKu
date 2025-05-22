package com.uwaisalqadri.mangaku.data.source.local

import com.uwaisalqadri.mangaku.data.source.local.entity.MangaObject

interface MangaLocalDataSource {
    fun getAllManga(): List<MangaObject>
    fun getMangaById(mangaId: String): List<MangaObject>
    fun addManga(manga: MangaObject)
    fun deleteManga(mangaId: String)
    fun clearAllManga()
    fun close()
}