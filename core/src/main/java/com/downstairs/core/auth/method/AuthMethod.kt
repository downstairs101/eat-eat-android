package com.downstairs.core.auth.method

import com.downstairs.core.auth.AuthResultData
import com.google.firebase.auth.AuthCredential

interface AuthMethod {
    suspend fun authorize(): AuthCredential

    fun onResult(authResultData: AuthResultData)
}