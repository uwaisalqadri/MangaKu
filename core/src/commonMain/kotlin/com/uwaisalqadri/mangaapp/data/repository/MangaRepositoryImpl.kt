package com.uwaisalqadri.mangaapp.data.repository

import com.uwaisalqadri.mangaapp.data.souce.remote.RemoteDataSource
import com.uwaisalqadri.mangaapp.data.souce.remote.response.Manga
import com.uwaisalqadri.mangaapp.domain.repository.MangaRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MangaRepositoryImpl(
    private val remoteDataSource: RemoteDataSource
): MangaRepository {

    override suspend fun fetchMangas(): Flow<List<Manga>> {
        return flow {
            val response = remoteDataSource.fetchMangas()
            emit(response.data)
        }
    }

    override suspend fun fetchTrendingMangas(): Flow<List<Manga>> {
        return flow {
            val response = remoteDataSource.fetchTrendingMangas()
            emit(response.data)
        }
    }

    override suspend fun fetchSearchMangas(query: String): Flow<List<Manga>> {
        return flow {
            val response = remoteDataSource.fetchSearchMangas(query)
            emit(response.data)
        }
    }

    override suspend fun fetchDetailManga(id: String): Flow<Manga?> {
        return flow {
            val response = remoteDataSource.fetchDetailManga(id)
            emit(response.data)
        }
    }

}