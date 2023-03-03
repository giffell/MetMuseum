package com.test.core_data.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SearchApiModel(
    val objectIDs: List<Int>,
    val total: Int
)