package com.uwaisalqadri.mangaku.domain.usecase.browse

import com.rickclephas.kmp.nativecoroutines.NativeCoroutineScope
import com.uwaisalqadri.mangaku.data.souce.remote.response.ApiException
import com.uwaisalqadri.mangaku.domain.base.ApiError
import com.uwaisalqadri.mangaku.domain.base.BaseInteractor
import com.uwaisalqadri.mangaku.domain.mapper.map
import com.uwaisalqadri.mangaku.domain.model.Manga
import com.uwaisalqadri.mangaku.domain.repository.MangaRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class BrowseInteractor(private val repository: MangaRepository): BrowseUseCase, BaseInteractor() {

//    @NativeCoroutineScope
//    private val coroutineScope: CoroutineScope = MainScope()

    override suspend fun getManga(): List<Manga> {
        val response = repository.getManga()
        return response.map()
//        return flow {
//            try {
//                val response = repository.getManga().map()
//                emit(response)
//            } catch (e: ApiException) {
//                throw ApiError(e.errorTitle, e.errorMessage)
//            } catch (e: Throwable) {
//                throw e
//            }
//        }
    }

    override suspend fun getTrendingManga(): Flow<List<Manga>> {
        return flow {
            try {
                val response = repository.getTrendingManga().map()
                emit(response)
            } catch (e: ApiException) {
                throw ApiError(e.errorTitle, e.errorMessage)
            } catch (e: Throwable) {
                throw e
            }
        }
    }
}