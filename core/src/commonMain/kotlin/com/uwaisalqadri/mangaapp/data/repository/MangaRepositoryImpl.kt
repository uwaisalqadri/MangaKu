package com.uwaisalqadri.mangaapp.data.repository

import com.uwaisalqadri.mangaapp.data.mapper.entity.MangaObjectMapper
import com.uwaisalqadri.mangaapp.data.mapper.response.MangaMapper
import com.uwaisalqadri.mangaapp.data.souce.local.LocalDataSource
import com.uwaisalqadri.mangaapp.data.souce.remote.RemoteDataSource
import com.uwaisalqadri.mangaapp.domain.model.Manga
import com.uwaisalqadri.mangaapp.domain.repository.MangaRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class MangaRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val mangaResponseMapper: MangaMapper,
    private val mangaObjectMapper: MangaObjectMapper
): MangaRepository {

    override suspend fun fetchMangas(): Flow<List<Manga>> {
        return flow {
            val response = remoteDataSource.fetchMangas().data.map { mangaResponseMapper.mapToDomain(it) }
            emit(response)
        }
    }

    override suspend fun fetchTrendingMangas(): Flow<List<Manga>> {
        return flow {
            val response = remoteDataSource.fetchTrendingMangas().data.map { mangaResponseMapper.mapToDomain(it) }
            emit(response)
        }
    }

    override suspend fun fetchSearchMangas(query: String): Flow<List<Manga>> {
        return flow {
            val response = remoteDataSource.fetchSearchMangas(query).data.map { mangaResponseMapper.mapToDomain(it) }
            emit(response)
        }
    }

    override suspend fun fetchDetailManga(id: String): Flow<Manga?> {
        return flow {
            val response = remoteDataSource.fetchDetailManga(id)
            val mapper = response.data?.let { mangaResponseMapper.mapToDomain(it) }
            emit(mapper)
        }
    }

    override suspend fun getFavoriteManga(): Flow<List<Manga>> {
        return localDataSource.getAllMangaAsFlowable().map {
            mangaObjectMapper.mapToListDomain(it)
        }
    }

    override fun addMangaFavorite(manga: Manga) {
        val mapper = mangaObjectMapper.mapToModel(manga)
        localDataSource.addManga(mapper)
    }

    override fun removeMangaFavorite(mangaId: Int) {
        localDataSource.deleteManga(mangaId)
    }

}