package com.downstairs.tosplit.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.downstairs.core.auth.AuthInteractor
import com.downstairs.core.auth.AuthMethod
import com.downstairs.core.auth.AuthResultData
import com.downstairs.core.extensions.launchIO
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val authInteractor: AuthInteractor) : ViewModel() {



    fun signIn(authMethod: AuthMethod) {
        viewModelScope.launchIO {
            val result = authInteractor.authorize(authMethod)
        }
    }

    fun processSignInResult(authResultData: AuthResultData) {
        authInteractor.processAuthResult(authResultData)
    }


}