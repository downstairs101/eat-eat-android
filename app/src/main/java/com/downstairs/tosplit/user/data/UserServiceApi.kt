package com.downstairs.tosplit.user.data

import retrofit2.http.GET

interface UserServiceApi {

    @GET("/user")
    suspend fun getUser(): UserRemote
}