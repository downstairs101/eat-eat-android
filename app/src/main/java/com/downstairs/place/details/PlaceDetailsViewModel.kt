package com.downstairs.place.details

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.downstairs.place.model.Place
import com.downstairs.place.model.PlaceRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class PlaceDetailsViewModel @Inject constructor(private val repository: PlaceRepository) :
    ViewModel() {

    private val _placeDetailsData = MutableLiveData<PlaceDetailsData>()
    private val _viewState = MutableLiveData<ViewState>()

    val placeDetailsData: LiveData<PlaceDetailsData> = _placeDetailsData
    val viewState: LiveData<ViewState> = _viewState

    fun fetchPlace(placeId: Long) {
        if (placeId <= 0) {
            viewToWriteState()
        } else {
            loadPlace(placeId)
        }
    }

    private fun loadPlace(placeId: Long) {
        viewModelScope.launch {
            val place = repository.getPlace(placeId)
            place?.also { bindPlace(it) }
        }
    }

    private fun bindPlace(place: Place) {
        _placeDetailsData.postValue(
            PlaceDetailsData(
                place.id,
                ObservableField(place.name),
                ObservableField(place.category),
                ObservableField(place.description)
            )
        )
    }

    fun savePlace(place: Place) {
        viewModelScope.launch {
            repository.insert(place)

            toReadOnlyState()
        }
    }

    private fun toReadOnlyState() {
        _viewState.postValue(ViewState(true))
    }

    fun viewToWriteState() {
        _viewState.postValue(ViewState(false))
    }

    class ViewState(isReadOnly: Boolean) {
        val isInWriteMode = !isReadOnly
    }
}