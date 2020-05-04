package com.downstairs.split

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.downstairs.eatat.core.extensions.launchIO
import com.downstairs.split.data.SplitUiModel
import javax.inject.Inject

class SplitsViewModel @Inject constructor(private val splitInteractor: SplitInteractor) : ViewModel() {

    private val mutableSplits = MutableLiveData<List<SplitUiModel>>()
    val splits: LiveData<List<SplitUiModel>> = mutableSplits

    init {
        viewModelScope.launchIO {
            loadSplits()
        }
    }

    private suspend fun loadSplits() {
        val result = splitInteractor.fetchSpits(1)
        result.onSuccess { onSplitResult(it) }
    }

    private fun onSplitResult(splitList: List<Split>) {
        val uiSplits = splitList.map {
            SplitUiModel.fromDomain(it)
        }

        mutableSplits.postValue(uiSplits)
    }
}