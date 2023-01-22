package com.uwaisalqadri.mangaku.presentation

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch

suspend fun <U> collectFlow(outputStateFlow: MutableStateFlow<Result<U>>, block: suspend () -> Flow<U>) {
    block.invoke().catch { cause: Throwable ->
        outputStateFlow.emit(Result.fail(cause, cause.message))
    }.collect {
        if (it is List<*>) {
            if (it.isEmpty()) outputStateFlow.emit(Result.empty())
            else outputStateFlow.emit(Result.success(it))
        }

        if (it != null) {
            outputStateFlow.emit(Result.success(it))
        }

        if (it == null) {
            outputStateFlow.emit(Result.empty())
        }
    }
}