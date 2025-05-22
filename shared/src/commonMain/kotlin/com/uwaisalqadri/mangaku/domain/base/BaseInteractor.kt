package com.uwaisalqadri.mangaku.domain.base

import com.uwaisalqadri.mangaku.data.source.remote.response.ApiExceptionResponse
import com.uwaisalqadri.mangaku.domain.model.ApiError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlin.coroutines.CoroutineContext

fun <T>executing(
    context: CoroutineContext = Dispatchers.Default,
    block: suspend () -> T
): Flow<T> {
    return flow {
        try {
            val out = block.invoke()
            emit(out)
        } catch (e: ApiExceptionResponse) {
            throw ApiError(e.errorTitle, e.errorMessage)
        } catch (e: Throwable) {
            throw e
        }
    }.flowOn(context)
}
