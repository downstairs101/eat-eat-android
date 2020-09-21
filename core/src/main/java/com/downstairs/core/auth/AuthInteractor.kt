package com.downstairs.core.auth

import javax.inject.Inject

class AuthInteractor @Inject constructor(private val firebase: FirebaseClient) {

    suspend fun authorize(authMethod: AuthMethod): AuthResult {
        if (isUserAlreadyAuthorized()) return AuthResult.Authorized

        return try {
            firebase.authorize(authMethod)
            AuthResult.Authorized
        } catch (error: Throwable) {
            AuthResult.Unauthorized
        }
    }

    private fun isUserAlreadyAuthorized() = firebase.isUserAuthorized()

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