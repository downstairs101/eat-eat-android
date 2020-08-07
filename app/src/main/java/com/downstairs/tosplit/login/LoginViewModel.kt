package com.downstairs.tosplit.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.downstairs.core.auth.AuthMethod
import com.downstairs.core.auth.AuthResultData
import com.downstairs.core.auth.FirebaseClient
import com.downstairs.core.extensions.launchIO

class LoginViewModel : ViewModel() {
    val firebaeClient = FirebaseClient()

    fun signIn(authMethod: AuthMethod) {
        viewModelScope.launchIO {
            firebaeClient.authorize(authMethod)
        }
    }

    fun processSignInResult(authResultData: AuthResultData) {
        firebaeClient.onResult(authResultData)
    }


}