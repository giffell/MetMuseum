package com.test.core_data.repositories

import com.skydoves.sandwich.suspendOnSuccess
import com.test.common.network.AppCoroutineDispatchers
import com.test.common.network.Dispatcher
import com.test.core_data.api.MetMuseumDataSource
import com.test.core_data.mappers.asDomain
import com.test.core_domain.repositories.MetMuseumRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MetMuseumRepositoryImpl @Inject constructor(
    @Dispatcher(AppCoroutineDispatchers.IO) private val coroutineDispatcher: CoroutineDispatcher,
    private val metMuseumDataSource: MetMuseumDataSource
) : MetMuseumRepository {

    override fun getSearchResult(searchQuery: String) = flow {
        metMuseumDataSource.search(searchQuery)
            .suspendOnSuccess {
                emit(data.asDomain())
            }
    }.flowOn(coroutineDispatcher)

    override fun getObjectDetails(objectId: Long) = flow {
        metMuseumDataSource.getObjectDetailsById(objectId)
            .suspendOnSuccess {
                emit(data.asDomain())
            }
    }.flowOn(coroutineDispatcher)
}
