package com.test.core_data.mappers

import com.test.core_domain.model.ObjectDetailsDomainModel
import com.test.core_domain.model.SearchDomainModel
import com.test.core_network.dto.ObjectDetailsApiModel
import com.test.core_network.dto.SearchApiModel

fun SearchApiModel.asDomain() = SearchDomainModel(objectIDs = objectIDs)

fun ObjectDetailsApiModel.asDomain(): ObjectDetailsDomainModel {
    return ObjectDetailsDomainModel(
        objectID = objectID
    )
}