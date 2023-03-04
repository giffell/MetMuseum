package com.test.core_domain

import com.test.common.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart

abstract class FlowUseCase<in P, R>(private val coroutineDispatcher: CoroutineDispatcher) {

    protected abstract fun execute(params: P): Flow<Result<R>>

    operator fun invoke(params: P): Flow<Result<R>> =
        execute(params)
            .onStart { emit(Result.Loading(true)) }
            .catch {
                emit(Result.Error(it))
            }.flowOn(coroutineDispatcher)
}