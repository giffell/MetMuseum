package com.test.core_domain.repositories

import com.test.core_domain.model.ObjectDetailsDomainModel
import com.test.core_domain.model.SearchDomainModel
import kotlinx.coroutines.flow.Flow

interface MetMuseumRepository {

    fun getSearchResult(searchQuery: String): Flow<SearchDomainModel>

    fun getObjectDetails(objectId: Long): Flow<ObjectDetailsDomainModel>
}