package com.downstairs.tosplit.splash

import androidx.core.widget.AutoScrollHelper
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class SplashViewModel @Inject constructor(private val splashInteractor: SplashInteractor) :
    ViewModel() {

    fun checkUserStatus() {
        viewModelScope.launch {
            val userStatus = splashInteractor.checkUserStatus()

            onUserStatusResult(userStatus)
        }
    }

    private fun onUserStatusResult(userStatus: UserStatus) {
        when(userStatus){
            is UserStatus.Authorized-> print("")
        }
    }

}