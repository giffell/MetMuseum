package com.test.core_model

data class ObjectDetailsInfo(
    val additionalImages: List<String>?,
    val culture: String,
    val department: String,
    val objectID: Int,
    val primaryImage: String,
    val repository: String,
    val title: String
)
