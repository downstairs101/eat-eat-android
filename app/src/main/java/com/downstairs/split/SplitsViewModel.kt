package com.downstairs.split

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class SplitsViewModel @Inject constructor() : ViewModel() {

    private val mutableSplits = MutableLiveData<List<SplitUiModel>>()
    val splits: LiveData<List<SplitUiModel>> = mutableSplits

    init {
        loadSplits()
    }

    private fun loadSplits() {
        val splits = listOf(
            SplitUiModel("Edgar", "R$ 350,00"),
            SplitUiModel("Allan Moreira", "R$ 150,00"),
            SplitUiModel("Zezinho da esquina", "R$ 550,00"),
            SplitUiModel("Seu João", "R$ 330,00")
        )

        mutableSplits.postValue(splits)
    }
}