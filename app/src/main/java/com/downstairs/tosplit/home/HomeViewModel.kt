package com.downstairs.tosplit.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.downstairs.tosplit.user.UserComplianceInteractor
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(userComplianceInteractor: UserComplianceInteractor) : ViewModel() {

    init {
        viewModelScope.launch {
            userComplianceInteractor.checkUserCompliance()
        }
    }
}