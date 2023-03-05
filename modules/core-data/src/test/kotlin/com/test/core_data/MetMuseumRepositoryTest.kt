package com.test.core_data

import app.cash.turbine.test
import com.nhaarman.mockitokotlin2.atLeastOnce
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import com.nhaarman.mockitokotlin2.whenever
import com.skydoves.sandwich.ApiResponse
import com.test.core_data.errors.NotValidObjectError
import com.test.core_data.mappers.asDomain
import com.test.core_data.repositories.MetMuseumRepositoryImpl
import com.test.core_network.api.MetMuseumDataSource
import com.test.core_network.dto.SearchApiModel
import com.test.core_test.MainCoroutinesRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Response

@OptIn(ExperimentalCoroutinesApi::class)
class MetMuseumRepositoryTest {

    private lateinit var repository: MetMuseumRepositoryImpl
    private val metMuseumDataSource: MetMuseumDataSource = mock()

    @get:Rule
    val coroutinesRule = MainCoroutinesRule()

    @ExperimentalCoroutinesApi
    @Before
    fun setup() {
        repository = MetMuseumRepositoryImpl(
            coroutinesRule.testDispatcher, metMuseumDataSource
        )
    }

    @Test
    fun `get search results success`() = runTest {
        val mockSearchQuery = "sunflower"
        val mockList = listOf(1, 2, 3)
        val mockSearchApiModel = SearchApiModel(mockList, mockList.size)
        whenever(metMuseumDataSource.search(mockSearchQuery)).thenReturn(
            ApiResponse.of {
                Response.success(
                    mockSearchApiModel
                )
            }
        )
        repository.getSearchResult(mockSearchQuery).test {
            val expectedItem = awaitItem()
            assertEquals(expectedItem.objectIDs?.size, mockSearchApiModel.asDomain().objectIDs?.size)
            awaitComplete()
        }

        verify(metMuseumDataSource, atLeastOnce()).search(mockSearchQuery)
        verifyNoMoreInteractions(metMuseumDataSource)
    }

    @Test
    fun `get search error`() = runTest {
        val mockSearchQuery = "sunflower"
        whenever(metMuseumDataSource.search(mockSearchQuery)).thenReturn(
            ApiResponse.error(Throwable("Error"))
        )
        repository.getSearchResult(mockSearchQuery).test {
            val expectedItem = awaitItem()
            assertEquals(expectedItem.objectIDs, null)
            awaitComplete()
        }

        verify(metMuseumDataSource, atLeastOnce()).search(mockSearchQuery)
        verifyNoMoreInteractions(metMuseumDataSource)

    }

    @Test
    fun `get object by id success`() = runTest {
        val mockObjectId = 123
        val mockObjectTitle = "Mock Title"
        val mockObjectDetailsApi = MockObjectDetails.createMockObjectDetailsApiModel(mockObjectId, mockObjectTitle)
        whenever(metMuseumDataSource.getObjectDetailsById(mockObjectId)).thenReturn(
            ApiResponse.of {
                Response.success(
                    mockObjectDetailsApi
                )
            }
        )
        repository.getObjectDetails(mockObjectId).test {
            val expectedItem = awaitItem()
            assertEquals(expectedItem.objectID, mockObjectDetailsApi.asDomain().objectID)
            assertEquals(expectedItem.title, mockObjectDetailsApi.asDomain().title)
            awaitComplete()
        }
    }

    @Test
    fun `get object by id error`() = runTest {
        val mockObjectId = 123
        val mockException = NotValidObjectError()
        whenever(metMuseumDataSource.getObjectDetailsById(mockObjectId)).thenReturn(
            ApiResponse.error(mockException)
        )
        repository.getObjectDetails(mockObjectId).test {
            val expectedItem = awaitError()
            assertEquals(expectedItem.message, mockException.message)
        }
    }
}