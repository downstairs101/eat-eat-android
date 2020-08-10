package com.downstairs.tosplit.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.downstairs.core.auth.AuthInteractor
import com.downstairs.core.auth.AuthMethod
import com.downstairs.core.auth.AuthResult
import com.downstairs.core.auth.AuthResultData
import com.downstairs.core.extensions.launchIO
import com.downstairs.core.tools.Instruction
import com.downstairs.core.tools.Navigation
import com.downstairs.core.tools.ViewInstruction
import com.downstairs.tosplit.R
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val instruction: LoginViewInstruction,
    private val authInteractor: AuthInteractor
) : ViewModel() {

    private val _viewInstruction = MutableLiveData<Instruction>()
    val viewInstruction: LiveData<Instruction> = _viewInstruction

    fun signIn(authMethod: AuthMethod) {
        viewModelScope.launchIO {
            val result = authInteractor.authorize(authMethod)

            if (result is AuthResult.Authorized) {
                authorizedUser()
            } else {
                unauthorizedUser()
            }
        }
    }

    private fun authorizedUser() {
        _viewInstruction.postValue(instruction.navigateToHome())
    }

    private fun unauthorizedUser() {

    }

    fun processSignInResult(authResultData: AuthResultData) {
        authInteractor.processAuthResult(authResultData)
    }
}

class LoginViewInstruction @Inject constructor() : ViewInstruction() {

    fun navigateToHome() = Navigation(R.id.fromLoginToHome)
}