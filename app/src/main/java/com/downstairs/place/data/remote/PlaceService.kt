package com.downstairs.place.data.remote

import retrofit2.http.GET

interface PlaceService {

    @GET("/places")
    fun list(): List<PlaceResponse>
}