package com.downstairs.tosplit.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class SplashViewModel @Inject constructor(private val splashInteractor: SplashInteractor) :
    ViewModel() {

    fun checkUserStatus() {
        viewModelScope.launch {
            splashInteractor.checkUserStatus()
        }
    }

}