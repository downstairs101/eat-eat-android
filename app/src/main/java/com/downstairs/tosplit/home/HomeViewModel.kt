package com.downstairs.tosplit.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.downstairs.tosplit.user.UserComplianceInteractor
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject
constructor(
    private val userComplianceInteractor: UserComplianceInteractor
) : ViewModel() {

    init {
        checkUserCompliance()
    }

    private fun checkUserCompliance() {
        viewModelScope.launch {
            userComplianceInteractor.checkUserCompliance()
        }
    }
}