package com.test.core_domain.model

data class ObjectDetailsDomainModel(
    val additionalImages: List<String>? = emptyList(),
    val culture: String = "",
    val department: String = "",
    val objectID: Int,
    val primaryImage: String = "",
    val repository: String = "",
    val title: String = ""
)