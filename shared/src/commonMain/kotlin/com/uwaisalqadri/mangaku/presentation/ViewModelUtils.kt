package com.uwaisalqadri.mangaku.presentation

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch

suspend fun <U> collectFlow(outputStateFlow: MutableStateFlow<ViewState<U>>, block: suspend () -> Flow<U>) {
    block.invoke().catch { cause: Throwable ->
        outputStateFlow.emit(ViewState.fail(cause, cause.message))
    }.collect {
        if (it is List<*>) {
            if (it.isEmpty()) outputStateFlow.emit(ViewState.empty())
            else outputStateFlow.emit(ViewState.success(it))
        }

        if (it != null) {
            outputStateFlow.emit(ViewState.success(it))
        }

        if (it == null) {
            outputStateFlow.emit(ViewState.empty())
        }
    }
}