package com.downstairs.tosplit.user

import retrofit2.http.GET

interface UserServiceApi {

    @GET("/user")
    suspend fun getUser(): UserRemote
}