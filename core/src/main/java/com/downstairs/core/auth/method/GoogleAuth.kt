package com.downstairs.core.auth.method

import androidx.fragment.app.Fragment
import com.downstairs.core.R
import com.downstairs.core.auth.AuthResultData
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.GoogleAuthProvider
import kotlin.coroutines.Continuation
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class GoogleAuth(private val fragment: Fragment) : AuthMethod {

    private var continuation: Continuation<AuthCredential>? = null

    companion object {
        const val GOOGLE_AUTH_REQUEST_CODE = 111
    }

    override suspend fun authorize() = suspendCoroutine<AuthCredential> {
        continuation = it
        performGoogleSignIn()
    }

    private fun performGoogleSignIn() {
        val signInClient = GoogleSignIn.getClient(fragment.requireContext(), getSignInOptions())
        val signInIntent = signInClient.signInIntent
        fragment.startActivityForResult(signInIntent, GOOGLE_AUTH_REQUEST_CODE)
    }

    override fun onResult(authResultData: AuthResultData) {
        GoogleSignIn.getSignedInAccountFromIntent(authResultData.data)

            .addOnSuccessListener { signInAccount -> onAuthSuccess(signInAccount) }
            .addOnFailureListener { error -> onAuthFailure(error) }
    }

    private fun onAuthSuccess(signInAccount: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(signInAccount.idToken, null)
        continuation?.resumeWith(Result.success(credential))
    }

    private fun onAuthFailure(throwable: Throwable) {
        if (throwable is ApiException) {
            continuation?.resumeWithException(parseError(throwable))
        } else {
            continuation?.resumeWithException(throwable)
        }
    }

    private fun parseError(apiException: ApiException): Throwable {
        return when (apiException.statusCode) {
            10 -> AuthClientPackageException()
            else -> Throwable("Google auth sign in failure")
        }
    }

    private fun getSignInOptions(): GoogleSignInOptions {
        val googleAuthClientId = fragment.getString(R.string.google_auth_client_id)

        return GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(googleAuthClientId)
            .requestEmail()
            .build()
    }
}

class AuthClientPackageException : Throwable("The client id may be wrong")