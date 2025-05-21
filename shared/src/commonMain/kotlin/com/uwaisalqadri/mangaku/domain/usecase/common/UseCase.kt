package com.uwaisalqadri.mangaku.domain.usecase.common

import kotlinx.coroutines.flow.Flow

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
