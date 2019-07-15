package com.downstairs.place.model

class PlaceRepository(private val placeDAO: PlaceDAO) {

    suspend fun get(placeId: Int) = placeDAO.findById(placeId)

    suspend fun insert() = placeDAO.insertPlace(Place(0, "EdFines", "Pub", "Nice"))
}