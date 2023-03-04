package com.test.core_network.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SearchApiModel(
    val objectIDs: List<Int>,
    val total: Int
)