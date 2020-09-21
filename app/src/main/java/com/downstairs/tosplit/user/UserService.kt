package com.downstairs.tosplit.user

import retrofit2.http.GET

interface UserService {

    @GET("/user")
    suspend fun getUser(): UserRemote
}