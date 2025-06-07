package com.uwaisalqadri.mangaku.domain.utils

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlin.coroutines.CoroutineContext

interface UseCase<in P, R> {
    fun execute(request: P): Flow<R>
}

class AnyUseCase<P, R>(
    private val execution: (P) -> Flow<R>
) : UseCase<P, R> {
    override fun execute(request: P): Flow<R> = execution(request)
}

interface ThrowingUseCase<in P, R> {
    fun execute(request: P): Flow<Result<R>>
}

class AnyThrowingUseCase<P, R>(
    private val execution: (P) -> Flow<Result<R>>
) : ThrowingUseCase<P, R> {
    override fun execute(request: P): Flow<Result<R>> = execution(request)
}

fun <R> UseCase<Unit, R>.execute(): Flow<R> = execute(Unit)

fun <R> ThrowingUseCase<Unit, R>.execute(): Flow<Result<R>> = execute(Unit)

fun <P, R> UseCase<P, R>.erased(): AnyUseCase<P, R> = AnyUseCase(this::execute)

fun <P, R> UseCase<P, R>.executing(
    dispatchers: CoroutineContext = Dispatchers.Default,
    block: suspend () -> R
): Flow<R> {
    return flow {
        try {
            val out = block.invoke()
            emit(out)
        } catch (e: Throwable) {
            throw e
        }
    }.flowOn(dispatchers)
}