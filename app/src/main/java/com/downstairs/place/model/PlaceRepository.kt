package com.downstairs.place.model

import javax.inject.Inject

class PlaceRepository {

    @Inject
    lateinit var placeDAO: PlaceDAO

    suspend fun get(placeId: Int) = placeDAO.findById(placeId)

    suspend fun insert() = placeDAO.insertPlace(Place(0, "EdFines", "Pub", "Nice"))
}