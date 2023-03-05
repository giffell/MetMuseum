@file:OptIn(ExperimentalCoroutinesApi::class)

package com.test.core_domain

import app.cash.turbine.test
import com.nhaarman.mockitokotlin2.atLeastOnce
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import com.nhaarman.mockitokotlin2.whenever
import com.test.common.Result
import com.test.core_domain.model.ObjectDetailsDomainModel
import com.test.core_domain.repositories.MetMuseumRepository
import com.test.core_domain.usecases.GetObjectDetailsUseCase
import com.test.core_test.MainCoroutinesRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GetObjectDetailsUseCaseTest {

    @get:Rule
    val coroutinesRule = MainCoroutinesRule()

    private val metMuseumRepository: MetMuseumRepository = mock()

    private lateinit var useCase: GetObjectDetailsUseCase

    @Before
    fun setup() {
        useCase = GetObjectDetailsUseCase(coroutinesRule.testDispatcher, metMuseumRepository)
    }

    @Test
    fun `check get object details success`() = runTest {
        val mockObjectId = 123
        val mockUseCaseParam = GetObjectDetailsUseCase.Params(mockObjectId)
        whenever(metMuseumRepository.getObjectDetails(mockUseCaseParam.objectId)).thenReturn(
            flowOf(ObjectDetailsDomainModel(emptyList(), "", "", mockObjectId, "", ""))
        )

        useCase(mockUseCaseParam).test {
            val expectedLoadingResult = awaitItem()
            val expectedSuccessResult = awaitItem()
            assertEquals((expectedLoadingResult as? Result.Loading)?.showProgress, true)
            assertEquals((expectedSuccessResult as? Result.Success)?.data?.objectID, 123)

            awaitComplete()
        }

        verify(metMuseumRepository, atLeastOnce()).getObjectDetails(mockObjectId)
        verifyNoMoreInteractions(metMuseumRepository)

    }

    @Test
    fun `check get object details error`() = runTest {
        val mockObjectId = 123
        val mockUseCaseParam = GetObjectDetailsUseCase.Params(mockObjectId)
        val mockedErrorMessage = "Not a valid object"
        whenever(metMuseumRepository.getObjectDetails(mockUseCaseParam.objectId)).thenReturn(
            flow { throw Throwable(mockedErrorMessage) }
        )

        useCase(mockUseCaseParam).test {
            val expectedLoadingResult = awaitItem()
            val expectedErrorResult = awaitItem()
            assertEquals((expectedLoadingResult as? Result.Loading)?.showProgress, true)
            assertEquals((expectedErrorResult as? Result.Error)?.exception?.message, mockedErrorMessage)
            awaitComplete()
        }

        verify(metMuseumRepository, atLeastOnce()).getObjectDetails(mockObjectId)
        verifyNoMoreInteractions(metMuseumRepository)

    }
}