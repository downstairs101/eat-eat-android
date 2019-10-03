package com.downstairs.place.data.remote

import retrofit2.http.GET

interface PlaceService {

    @GET
    fun list(): List<PlaceResponse>
}