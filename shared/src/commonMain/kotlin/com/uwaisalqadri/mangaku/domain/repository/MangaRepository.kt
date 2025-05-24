package com.uwaisalqadri.mangaku.domain.repository

import com.uwaisalqadri.mangaku.domain.model.Manga

interface MangaRepository {
    suspend fun getManga(): List<Manga>
    suspend fun getTrendingManga(): List<Manga>
    suspend fun getSearchManga(query: String): List<Manga>
    suspend fun getDetailManga(mangaId: String): Manga
    suspend fun getFavoriteManga(): List<Manga>
    suspend fun getFavoriteMangaById(mangaId: String): List<Manga>
    suspend fun addMangaFavorite(manga: Manga)
    suspend fun deleteMangaFavorite(mangaId: String)
}