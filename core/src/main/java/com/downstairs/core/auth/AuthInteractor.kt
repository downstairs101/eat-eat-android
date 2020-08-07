package com.downstairs.core.auth

import javax.inject.Inject

class AuthInteractor @Inject constructor(private val firebase: FirebaseClient) {

    suspend fun authorize(authMethod: AuthMethod): AuthResult {
        return try {
            firebase.authorize(authMethod)
            AuthResult.Authorized
        } catch (error: Throwable) {
            AuthResult.Unauthorized
        }
    }

    suspend fun getIdToken() {
        try {
            firebase.getUserIdToken()
        } catch (error: Throwable) {

        }
    }

    fun processAuthResult(authResultData: AuthResultData) {
        firebase.onResult(authResultData)
    }
}