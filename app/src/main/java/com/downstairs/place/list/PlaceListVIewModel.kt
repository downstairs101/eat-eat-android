package com.downstairs.place.list

import androidx.lifecycle.ViewModel
import com.downstairs.place.model.PlaceRepository
import javax.inject.Inject

class PlaceListVIewModel @Inject constructor(private val repository: PlaceRepository) :
    ViewModel() {

    fun loadPlaces(){
    }

}