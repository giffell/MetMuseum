package com.test.core_data.repositories

import com.skydoves.sandwich.message
import com.skydoves.sandwich.suspendOnFailure
import com.skydoves.sandwich.suspendOnSuccess
import com.test.common.network.AppCoroutineDispatchers
import com.test.common.network.Dispatcher
import com.test.core_data.mappers.asDomain
import com.test.core_domain.model.SearchDomainModel
import com.test.core_domain.repositories.MetMuseumRepository
import com.test.core_network.api.MetMuseumDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber
import javax.inject.Inject

class MetMuseumRepositoryImpl @Inject constructor(
    @Dispatcher(AppCoroutineDispatchers.IO) private val coroutineDispatcher: CoroutineDispatcher,
    private val metMuseumDataSource: MetMuseumDataSource
) : MetMuseumRepository {

    override fun getSearchResult(searchQuery: String) = flow {
        metMuseumDataSource.search(searchQuery)
            .suspendOnSuccess { emit(data.asDomain()) }
            .suspendOnFailure {
                Timber.e(message())
                emit(SearchDomainModel())
            }
    }.flowOn(coroutineDispatcher)

    override fun getObjectDetails(objectId: Long) = flow {
        metMuseumDataSource.getObjectDetailsById(objectId)
            .suspendOnSuccess {
                emit(data.asDomain())
            }
    }.flowOn(coroutineDispatcher)
}
