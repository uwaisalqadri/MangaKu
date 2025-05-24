package com.uwaisalqadri.mangaku.domain.base

import com.uwaisalqadri.mangaku.data.source.remote.response.ApiExceptionResponse
import com.uwaisalqadri.mangaku.domain.model.ApiError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlin.coroutines.CoroutineContext

interface UseCase<in P, R> {
    fun execute(parameter: P): Flow<R>
}

fun <R> UseCase<Unit, R>.execute(): Flow<R> = execute(Unit)

class AnyUseCase<P, R>(
    private val execution: (P) -> Flow<R>
) : UseCase<P, R> {
    override fun execute(parameter: P): Flow<R> = execution(parameter)
}

interface ThrowingUseCase<in P, R> {
    fun execute(parameter: P): Flow<Result<R>>
}

fun <R> ThrowingUseCase<Unit, R>.execute(): Flow<Result<R>> = execute(Unit)

class AnyThrowingUseCase<P, R>(
    private val execution: (P) -> Flow<Result<R>>
) : ThrowingUseCase<P, R> {
    override fun execute(parameter: P): Flow<Result<R>> = execution(parameter)
}

fun <P, R> UseCase<P, R>.erased(): AnyUseCase<P, R> = AnyUseCase(this::execute)

fun <P, R>UseCase<P, R>.executing(
    dispatchers: CoroutineContext = Dispatchers.Default,
    block: suspend () -> R
): Flow<R> {
    return flow {
        try {
            val out = block.invoke()
            emit(out)
        } catch (e: ApiExceptionResponse) {
            throw ApiError(e.errorTitle, e.errorMessage)
        } catch (e: Throwable) {
            throw e
        }
    }.flowOn(dispatchers)
}
