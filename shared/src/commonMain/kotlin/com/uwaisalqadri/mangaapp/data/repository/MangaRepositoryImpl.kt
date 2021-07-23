package com.uwaisalqadri.mangaapp.data.repository

import com.uwaisalqadri.mangaapp.data.souce.remote.ApiService
import com.uwaisalqadri.mangaapp.data.souce.remote.response.Manga
import com.uwaisalqadri.mangaapp.data.souce.remote.response.MangaResponse
import com.uwaisalqadri.mangaapp.domain.repository.MangaRepository
import com.uwaisalqadri.mangaapp.utils.Resource
import io.ktor.client.features.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MangaRepositoryImpl(private val apiService: ApiService): MangaRepository {

    override suspend fun fetchMangas(): Flow<Resource<List<Manga>>> {
        return flow {
            val response = apiService.fetchMangas()
            try {
                if (response.data.isNotEmpty()) {
                    emit(Resource.Success(response.data))
                } else {
                    emit(Resource.Empty)
                }
            } catch (e: ClientRequestException) {
                emit(Resource.Error(e.toString(), response.data))
            }
        }
    }

    override suspend fun fetchSearchMangas(query: String): Flow<Resource<List<Manga>>> {
        return flow {
            val response = apiService.fetchSearchMangas(query)
            try {
                if (response.data.isNotEmpty()) {
                    emit(Resource.Success(response.data))
                } else {
                    emit(Resource.Empty)
                }
            } catch (e: ClientRequestException) {
                emit(Resource.Error(e.toString(), response.data))
            }
        }
    }

    override suspend fun fetchDetailManga(id: String): Flow<Resource<Manga?>> {
        return flow {
            val response = apiService.fetchDetailManga(id)
            try {
                if (response.data != null) {
                    emit(Resource.Success(response.data))
                } else {
                    emit(Resource.Empty)
                }
            } catch (e: ClientRequestException) {
                emit(Resource.Error(e.toString(), response.data))
            }
        }
    }

}