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

class PlaceListViewModel
@Inject constructor(private val repository: PlaceRepository) : ViewModel() {

    private val mutablePlaces by lazy { MutableLiveData<List<PlaceListItem>>().also { loadPlaces() } }

    val places: LiveData<List<PlaceListItem>> = mutablePlaces

    fun updatePlaceList() {
        loadPlaces()
    }

    private fun loadPlaces() {
        viewModelScope.launch(context = Dispatchers.IO) {
            val places = withContext(coroutineContext) { repository.getAll() }

            val placeListItems = places.map { place ->
                PlaceListItem(place.id, place.name, place.category, place.description)
            }

            mutablePlaces.postValue(placeListItems)
        }
    }
}