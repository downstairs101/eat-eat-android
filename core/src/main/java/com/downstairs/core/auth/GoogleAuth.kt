package com.downstairs.core.auth

import android.app.Activity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.GoogleAuthProvider
import kotlin.coroutines.Continuation
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class GoogleAuth(private val activity: Activity) : AuthMethod {

    private var continuation: Continuation<AuthCredential>? = null

    companion object {
        const val GOOGLE_AUTH_REQUEST_CODE = 111
    }

    override suspend fun authorize() = suspendCoroutine<AuthCredential> {
        continuation = it
        performGoogleSignIn()
    }

    private fun performGoogleSignIn() {
        val signInClient = GoogleSignIn.getClient(activity, getSignInOptions())
        val signInIntent = signInClient.signInIntent
        activity.startActivityForResult(signInIntent, GOOGLE_AUTH_REQUEST_CODE)
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

    private fun onAuthFailure(error: Exception) {
        continuation?.resumeWithException(error)
    }

    private fun getSignInOptions() =
        GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("588997750243-5ml4tl6vng6euusii6460p19ot1u18l5.apps.googleusercontent.com")
            .requestEmail()
            .build()
}