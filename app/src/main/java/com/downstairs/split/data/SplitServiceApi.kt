package com.downstairs.split.data

import retrofit2.http.GET
import retrofit2.http.Path

interface SplitServiceApi {

    @GET("/splits/{id}")
    fun fetchSplits(@Path("id") userId: Int)
}