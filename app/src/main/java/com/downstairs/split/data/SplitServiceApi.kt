package com.downstairs.split.data

import retrofit2.http.GET

interface SplitServiceApi {

    @GET("split")
    suspend fun fetchSplits(): List<SplitRemote>
}