package com.downstairs.core.auth

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

    fun checkAuthStatus() = if (firebase.isUserUnauthorized()) {
        AuthResult.Authorized
    } else {
        AuthResult.Unauthorized
    }

    suspend fun getIdToken(): CredentialResult {
        return try {
            CredentialResult.ValidCredential(firebase.getUserIdToken())
        } catch (error: Throwable) {
            CredentialResult.InvalidCredential
        }
    }

    fun processAuthResult(authResultData: AuthResultData) {
        firebase.onResult(authResultData)
    }
}