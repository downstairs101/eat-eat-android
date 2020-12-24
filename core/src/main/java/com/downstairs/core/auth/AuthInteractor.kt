package com.downstairs.core.auth

import com.downstairs.core.auth.method.AuthMethod
import javax.inject.Inject

class AuthInteractor @Inject constructor(private val firebase: FirebaseClient) {

    suspend fun authorize(authMethod: AuthMethod): AuthResult {
        if (firebase.isUserAuthorized()) return AuthResult.Authorized

        return try {
            firebase.authorize(authMethod)
            AuthResult.Authorized
        } catch (error: Throwable) {
            AuthResult.Unauthorized
        }
    }

    fun checkAuthStatus() = if (firebase.isUserAuthorized()) {
        AuthResult.Authorized
    } else {
        AuthResult.Unauthorized
    }

    fun processAuthResult(authResultData: AuthResultData) {
        firebase.onResult(authResultData)
    }
}