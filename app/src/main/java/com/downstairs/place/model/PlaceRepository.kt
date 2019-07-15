package com.downstairs.place.model

class PlaceRepository(private val placeDAO: PlaceDAO) {

    suspend fun get(placeId: Int) = placeDAO.findById(placeId)

    suspend fun insert(place: Place) = placeDAO.insertPlace(place)
}