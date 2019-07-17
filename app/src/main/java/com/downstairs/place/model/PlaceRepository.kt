package com.downstairs.place.model

import javax.inject.Inject

class PlaceRepository @Inject constructor(private val placeDAO: PlaceDAO) {

    suspend fun getPlace(placeId: Int) = placeDAO.findById(placeId)

    suspend fun insert() = placeDAO.insertPlace(Place(name = "EdFines", category = "Pub", description = "Nice"))
}