package com.downstairs.place.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.downstairs.place.data.PlaceRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PlaceListVIewModel @Inject constructor(private val repository: PlaceRepository) :
    ViewModel() {

    private val _places by lazy { MutableLiveData<List<PlaceListItem>>().also { loadPlaces() } }

    fun places(): LiveData<List<PlaceListItem>> = _places

    fun updatePlaceList() {
        loadPlaces()
    }

    private fun loadPlaces() {
        viewModelScope.launch(context = Dispatchers.IO) {
            val places = withContext(coroutineContext) { repository.getAll() }

            val placeListItems = places.map { place ->
                PlaceListItem(place.id!!, place.name, place.category, place.description)
            }

            _places.postValue(placeListItems)
        }
    }
}