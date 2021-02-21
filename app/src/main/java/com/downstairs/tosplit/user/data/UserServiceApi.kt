package com.downstairs.tosplit.user.data

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserServiceApi {

    @GET("/user")
    suspend fun getUser(): UserRemote

    @POST("/user")
    suspend fun saveUser(@Body userRemote: UserRemote)
}