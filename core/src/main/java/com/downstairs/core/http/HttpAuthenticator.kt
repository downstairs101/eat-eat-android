package com.downstairs.core.http

import com.downstairs.core.auth.AuthInteractor
import com.downstairs.core.auth.CredentialResult
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject

class HttpAuthenticator @Inject
constructor(private val authInteractor: AuthInteractor) : Authenticator {

    companion object {
        const val AUTH_HEADER = "Authorization"
        const val ATTEMPTS_HEADER = "X-AttemptsHeader"
        const val MAX_ATTEMPTS = 2
    }

    override fun authenticate(route: Route?, response: Response): Request? {
        val request = response.request()

        return if (request.header(AUTH_HEADER) != null && isMaxAttemptsNotReached(request)) {
            authorizedRequest(request)
        } else {
            null
        }
    }

    private fun authorizedRequest(request: Request): Request? {
        val result = runBlocking { authInteractor.getIdToken() }

        return if (result is CredentialResult.ValidCredential) {
            request.newBuilder()
                .header(AUTH_HEADER, result.idToken)
                .header(ATTEMPTS_HEADER, getIncrementedAttempts(request))
                .build()
        } else {
            null
        }
    }

    private fun isMaxAttemptsNotReached(request: Request) = getAttempts(request) != MAX_ATTEMPTS

    private fun getIncrementedAttempts(request: Request): String {
        var attempts = getAttempts(request)
        return "${++attempts}"
    }

    private fun getAttempts(request: Request): Int {
        return (request.header(ATTEMPTS_HEADER) ?: "0").toInt()
    }
}