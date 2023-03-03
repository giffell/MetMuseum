package com.test.core_data.mappers

import com.test.core_data.dto.ObjectDetailsApiModel
import com.test.core_data.dto.SearchApiModel
import com.test.core_domain.model.ObjectDetailsDomainModel
import com.test.core_domain.model.SearchDomainModel

fun SearchApiModel.asDomain() = SearchDomainModel(objectIDs = objectIDs)

fun ObjectDetailsApiModel.asDomain(): ObjectDetailsDomainModel {
    return ObjectDetailsDomainModel(
        objectID = objectID
    )
}