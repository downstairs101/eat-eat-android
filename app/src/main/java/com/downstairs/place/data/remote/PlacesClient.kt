package com.downstairs.place.data.remote

import retrofit2.http.GET

interface PlacesClient {

    @GET("/places")
    suspend fun list(): List<PlaceResponse>
}