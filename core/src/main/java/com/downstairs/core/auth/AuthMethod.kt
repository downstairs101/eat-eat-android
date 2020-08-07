package com.downstairs.core.auth

import com.google.firebase.auth.AuthCredential

interface AuthMethod {
    suspend fun authorize(): AuthCredential

    fun onResult(authResultData: AuthResultData)
}