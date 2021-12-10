package com.uwaisalqadri.mangaku.data.repository

import com.uwaisalqadri.mangaku.domain.mapper.map
import com.uwaisalqadri.mangaku.data.souce.local.DefaultMangaLocalDataSource
import com.uwaisalqadri.mangaku.data.souce.local.MangaLocalDataSource
import com.uwaisalqadri.mangaku.data.souce.remote.DefaultMangaRemoteDataSource
import com.uwaisalqadri.mangaku.data.souce.remote.MangaRemoteDataSource
import com.uwaisalqadri.mangaku.domain.model.Manga
import com.uwaisalqadri.mangaku.domain.repository.MangaRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DefaultMangaRepository(
    private val mangaRemoteDataSource: MangaRemoteDataSource,
    private val mangaLocalDataSource: MangaLocalDataSource
): MangaRepository {

    override suspend fun getManga(): Flow<List<Manga>> {
        return flow {
            val response = mangaRemoteDataSource.fetchManga().data.map { it.map() }
            emit(response)
        }
    }

    override suspend fun getTrendingManga(): Flow<List<Manga>> {
        return flow {
            val response = mangaRemoteDataSource.fetchTrendingManga().data.map { it.map() }
            emit(response)
        }
    }

    override suspend fun getSearchManga(query: String): Flow<List<Manga>> {
        return flow {
            val response = mangaRemoteDataSource.fetchSearchManga(query).data.map { it.map() }
            emit(response)
        }
    }

    override suspend fun getDetailManga(id: String): Flow<Manga?> {
        return flow {
            val response = mangaRemoteDataSource.fetchDetailManga(id)
            val mapper = response.data?.map()
            emit(mapper)
        }
    }

    override suspend fun getFavoriteManga(): Flow<List<Manga>> {
        return flow {
            val result = mangaLocalDataSource.getAllManga().map { it.map() }
            emit(result)
        }
    }

    override suspend fun getFavoriteMangaById(mangaId: String): Flow<List<Manga>> {
        return flow {
            val result = mangaLocalDataSource.getMangaById(mangaId).map { it.map() }
            emit(result)
        }
    }

    override fun addMangaFavorite(manga: Manga) {
        val mapper = manga.map()
        mangaLocalDataSource.addManga(mapper)
    }

    override fun deleteMangaFavorite(mangaId: String) {
        mangaLocalDataSource.deleteManga(mangaId)
    }

}