package com.downstairs.place.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.downstairs.place.model.Place
import com.downstairs.place.model.PlaceRepository
import kotlinx.coroutines.async
import javax.inject.Inject

class PlaceListVIewModel @Inject constructor(private val repository: PlaceRepository) :
    ViewModel() {

    private val _places = MutableLiveData<List<Place>>()

    fun places(): LiveData<List<Place>> = _places

    fun loadPlaces() {
        viewModelScope.async {
            val places = repository.getAll()

            _places.postValue(places)
        }
    }

}