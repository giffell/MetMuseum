package com.test.core_domain.usecases

import com.test.common.Result
import com.test.common.network.AppCoroutineDispatchers
import com.test.common.network.Dispatcher
import com.test.core_domain.FlowUseCase
import com.test.core_domain.repositories.MetMuseumRepository
import com.test.core_model.SearchResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetSearchResultsUseCase @Inject constructor(
    @Dispatcher(AppCoroutineDispatchers.IO) private val coroutineDispatcher: CoroutineDispatcher,
    private val metMuseumRepository: MetMuseumRepository
) : FlowUseCase<GetSearchResultsUseCase.Params, SearchResult>(coroutineDispatcher) {

    class Params(val searchQuery: String)

    override fun execute(params: Params): Flow<Result<SearchResult>> {
        return metMuseumRepository.getSearchResult(params.searchQuery).map {
            if (it.objectIDs != null) {
                Result.Success(SearchResult(it.objectIDs))
            } else {
                Result.Error(Throwable("ObjectIds is null"))
            }
        }
    }
}