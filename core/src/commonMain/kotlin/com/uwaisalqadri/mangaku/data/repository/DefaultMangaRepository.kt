package com.uwaisalqadri.mangaku.data.repository

import com.uwaisalqadri.mangaku.domain.mapper.map
import com.uwaisalqadri.mangaku.data.souce.local.LocalDataSource
import com.uwaisalqadri.mangaku.data.souce.remote.RemoteDataSource
import com.uwaisalqadri.mangaku.domain.model.Manga
import com.uwaisalqadri.mangaku.domain.repository.MangaRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DefaultMangaRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
): MangaRepository {

    override suspend fun fetchMangas(): Flow<List<Manga>> {
        return flow {
            val response = remoteDataSource.fetchMangas().data.map { it.map() }
            emit(response)
        }
    }

    override suspend fun fetchTrendingMangas(): Flow<List<Manga>> {
        return flow {
            val response = remoteDataSource.fetchTrendingMangas().data.map { it.map() }
            emit(response)
        }
    }

    override suspend fun fetchSearchMangas(query: String): Flow<List<Manga>> {
        return flow {
            val response = remoteDataSource.fetchSearchMangas(query).data.map { it.map() }
            emit(response)
        }
    }

    override suspend fun fetchDetailManga(id: String): Flow<Manga?> {
        return flow {
            val response = remoteDataSource.fetchDetailManga(id)
            val mapper = response.data?.map()
            emit(mapper)
        }
    }

    override suspend fun getFavoriteManga(): Flow<List<Manga>> {
        return flow {
            val result = localDataSource.getAllManga().map { it.map() }
            emit(result)
        }
    }

    override suspend fun getFavoriteMangaById(mangaId: String): Flow<List<Manga>> {
        return flow {
            val result = localDataSource.getMangaById(mangaId).map { it.map() }
            emit(result)
        }
    }

    override fun addMangaFavorite(manga: Manga) {
        val mapper = manga.map()
        localDataSource.addManga(mapper)
    }

    override fun removeMangaFavorite(mangaId: String) {
        localDataSource.deleteManga(mangaId)
    }

}