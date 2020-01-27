package com.downstairs.place.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.downstairs.place.data.PlaceEntity
import com.downstairs.place.data.PlaceRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

class PlaceDetailsViewModel @Inject constructor(private val repository: PlaceRepository) :
    ViewModel() {

    private val mutablePlaceDetailsData = MutableLiveData<PlaceDetailsData>()
    val placeDetailsData: LiveData<PlaceDetailsData> = mutablePlaceDetailsData

    private val mutableViewState = MutableLiveData<ViewState>()
    val viewState: LiveData<ViewState> = mutableViewState

    fun fetchPlace(placeId: String) {
        if (placeId.isBlank()) {
            viewToWriteState()
        } else {
            loadPlace(placeId)
        }
    }

    private fun loadPlace(placeId: String) {
        toReadOnlyState()
        viewModelScope.launch(Dispatchers.IO) {
            val place = repository.getPlace(placeId)
            place?.also { bindPlace(it) }
        }
    }

    private fun bindPlace(placeEntity: PlaceEntity) {
        mutablePlaceDetailsData.postValue(
            PlaceDetailsData(
                placeEntity.id,
                placeEntity.name,
                placeEntity.category,
                placeEntity.description
            )
        )
    }

    fun savePlace(placeDetailsData: PlaceDetailsData) {
        viewModelScope.launch(Dispatchers.IO) {

            val place = PlaceEntity(
                getPlaceId(),
                placeDetailsData.name,
                placeDetailsData.category,
                placeDetailsData.description
            )

            repository.insert(place)
            toReadOnlyState()
        }
    }

    private fun getPlaceId(): String {
        val placeDetailsData = mutablePlaceDetailsData.value

        return if (placeDetailsData?.id != null) {
            placeDetailsData.id
        } else {
            UUID.randomUUID().toString()
        }
    }

    private fun toReadOnlyState() {
        mutableViewState.postValue(ViewState.READ_ONLY_STATE)
    }

    fun viewToWriteState() {
        mutableViewState.postValue(ViewState.WRITE_STATE)
    }

    enum class ViewState {
        WRITE_STATE,
        READ_ONLY_STATE
    }
}