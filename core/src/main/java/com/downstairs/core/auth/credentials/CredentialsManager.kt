package com.downstairs.core.auth.credentials

import com.downstairs.core.auth.AuthManager
import javax.inject.Inject

class CredentialsManager @Inject constructor(private val firebase: AuthManager) {

    suspend fun getIdToken(): CredentialResult {
        return try {
            CredentialResult.ValidCredential(firebase.getUserIdToken())
        } catch (error: Throwable) {
            CredentialResult.InvalidCredential
        }
    }
}