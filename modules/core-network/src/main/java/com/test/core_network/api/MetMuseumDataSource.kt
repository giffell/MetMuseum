package com.test.core_network.api

import com.skydoves.sandwich.ApiResponse
import com.test.core_network.dto.ObjectDetailsApiModel
import com.test.core_network.dto.SearchApiModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MetMuseumDataSource {

    @GET(ApiConst.SEARCH)
    suspend fun search(
        @Query("q") searchQuery: String
    ): ApiResponse<SearchApiModel>

    @GET("${ApiConst.OBJECT_BY_ID}/{id}")
    suspend fun getObjectDetailsById(
        @Path("id") objectId: Int
    ): ApiResponse<ObjectDetailsApiModel>
}