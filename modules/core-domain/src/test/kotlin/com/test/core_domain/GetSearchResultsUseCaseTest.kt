@file:OptIn(ExperimentalCoroutinesApi::class)

package com.test.core_domain

import app.cash.turbine.test
import com.nhaarman.mockitokotlin2.atLeastOnce
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import com.nhaarman.mockitokotlin2.whenever
import com.test.common.Result
import com.test.core_domain.model.SearchDomainModel
import com.test.core_domain.repositories.MetMuseumRepository
import com.test.core_domain.usecases.GetSearchResultsUseCase
import com.test.core_test.MainCoroutinesRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GetSearchResultsUseCaseTest {

    @get:Rule
    val coroutinesRule = MainCoroutinesRule()

    private val metMuseumRepository: MetMuseumRepository = mock()

    private lateinit var useCase: GetSearchResultsUseCase

    @Before
    fun setup() {
        useCase = GetSearchResultsUseCase(coroutinesRule.testDispatcher, metMuseumRepository)
    }

    @Test
    fun `check search result success`() = runTest {
        val mockSearchQuery = "sunflower"
        val mockList = listOf(1, 2, 3)
        val mockUseCaseParam = GetSearchResultsUseCase.Params(mockSearchQuery)
        whenever(metMuseumRepository.getSearchResult(mockUseCaseParam.searchQuery)).thenReturn(
            flowOf(SearchDomainModel(mockList))
        )

        useCase(mockUseCaseParam).test {
            val expectedLoadingResult = awaitItem()
            val expectedSuccessResult = awaitItem()
            assertEquals((expectedLoadingResult as? Result.Loading)?.showProgress, true)
            assertEquals((expectedSuccessResult as? Result.Success)?.data?.objectIDs?.size, mockList.size)

            awaitComplete()
        }

        verify(metMuseumRepository, atLeastOnce()).getSearchResult(mockUseCaseParam.searchQuery)
        verifyNoMoreInteractions(metMuseumRepository)
    }

    @Test
    fun `check search result error`() = runTest {
        val mockSearchQuery = "sunflower"
        val mockedErrorMessage = "ObjectIds is null"
        val mockUseCaseParam = GetSearchResultsUseCase.Params(mockSearchQuery)
        whenever(metMuseumRepository.getSearchResult(mockUseCaseParam.searchQuery)).thenReturn(
            flow { throw Throwable(mockedErrorMessage) }
        )

        useCase(mockUseCaseParam).test {
            val expectedLoadingResult = awaitItem()
            val expectedErrorResult = awaitItem()
            assertEquals((expectedLoadingResult as? Result.Loading)?.showProgress, true)
            assertEquals((expectedErrorResult as? Result.Error)?.exception?.message, mockedErrorMessage)

            awaitComplete()
        }

        verify(metMuseumRepository, atLeastOnce()).getSearchResult(mockUseCaseParam.searchQuery)
        verifyNoMoreInteractions(metMuseumRepository)
    }
}