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
        const val MAX_ATTEMPTS = 2
    }

    override fun authenticate(route: Route?, response: Response): Request? {
        val request = response.request()

        return if (request.header(AUTH_HEADER) != null && isMaxAttemptsNotReached(response)) {
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
                .build()
        } else {
            null
        }
    }

    private fun isMaxAttemptsNotReached(response: Response) = getAttempts(response) <= MAX_ATTEMPTS

    private fun getAttempts(response: Response): Int {
        var priorResponse: Response? = response
        var attempts = 1
        while (priorResponse != null) {
            priorResponse = priorResponse.priorResponse()
            attempts++
        }
        return attempts
    }
}