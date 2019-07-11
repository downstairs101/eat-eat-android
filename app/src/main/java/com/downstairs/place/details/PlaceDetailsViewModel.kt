package com.downstairs.place.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PlaceDetailsViewModel : ViewModel() {

    private val viewEditableState: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>().also {
            it.value = false
        }
    }

    val editableState: LiveData<Boolean> = viewEditableState

    fun enterOnEditMode() {
        viewEditableState.value = true
    }


}