package com.downstairs.core.http

import com.downstairs.core.auth.CredentialResult
import com.downstairs.core.auth.CredentialsManager
import com.downstairs.core.http.HttpAuthenticator.Companion.AUTH_HEADER
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(private val credentialsManager: CredentialsManager) :
    Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val credentialResult = runBlocking { credentialsManager.getIdToken() }
        val idToken = parseCredentialResult(credentialResult)

        val request = chain.request().newBuilder().addHeader(AUTH_HEADER, idToken).build()

        return chain.proceed(request)
    }

    private fun parseCredentialResult(credentialResult: CredentialResult): String {
        return if (credentialResult is CredentialResult.ValidCredential) {
            credentialResult.idToken
        } else {
            ""
        }
    }

}