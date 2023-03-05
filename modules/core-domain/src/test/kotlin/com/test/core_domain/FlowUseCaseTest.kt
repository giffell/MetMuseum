@file:OptIn(ExperimentalCoroutinesApi::class)

package com.test.core_domain

import app.cash.turbine.test
import com.test.core_test.MainCoroutinesRule
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class FlowUseCaseTest {

    @get:Rule
    var coroutineRule = MainCoroutinesRule()

    @Test
    fun `useCase emits Result#Error`() = runTest {
        val useCase = ExceptionUseCase(coroutineRule.testDispatcher)
        useCase(Unit).test {
            val expectedLoadingItem = awaitItem()
            val expectedItem = awaitItem()
            assertEquals(expectedItem is com.test.common.Result.Error, true)
            awaitComplete()
        }
    }

    class ExceptionUseCase(dispatcher: CoroutineDispatcher) : FlowUseCase<Unit, Unit>(dispatcher) {
        override fun execute(params: Unit): Flow<com.test.common.Result.Error> = flow {
            throw Exception("Test exception")
        }
    }
}