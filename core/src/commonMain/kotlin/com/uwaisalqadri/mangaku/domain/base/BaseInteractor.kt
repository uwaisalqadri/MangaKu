package com.uwaisalqadri.mangaku.domain.base

import com.rickclephas.kmp.nativecoroutines.NativeCoroutinesIgnore
import com.uwaisalqadri.mangaku.data.souce.remote.response.ApiException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlin.coroutines.CoroutineContext

abstract class BaseInteractor {

    @NativeCoroutinesIgnore
    fun <T>execute(
        context: CoroutineContext = Dispatchers.Default,
        block: suspend () -> T
    ): Flow<T> {
        return flow {
            try {
                val out = block.invoke()
                emit(out)
            } catch (e: ApiException) {
                throw ApiError(e.errorTitle, e.errorMessage)
            }
            catch (e: Throwable) {
                throw e
            }
        }.flowOn(context)
    }
}
