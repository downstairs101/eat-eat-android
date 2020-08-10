package com.downstairs.core.auth

import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class FirebaseClient @Inject constructor() {

    private var authMethod: AuthMethod? = null

    suspend fun authorize(authMethod: AuthMethod) {
        this.authMethod = authMethod

        val authCredential = authMethod.authorize()
        signInWithCredential(authCredential)
    }

    suspend fun getUserIdToken(): String {
        val currentUser = Firebase.auth.currentUser
            ?: throw Throwable("Current user is null, please, perform authorization")

        return suspendCoroutine { continuation ->
            currentUser.getIdToken(true)

                .addOnSuccessListener { tokenResult ->
                    continuation.resumeWith(Result.success(tokenResult.token!!))
                }

                .addOnFailureListener {
                    continuation.resumeWithException(it)
                }
        }
    }

    private suspend fun signInWithCredential(credential: AuthCredential) {
        return suspendCoroutine { continuation ->
            Firebase.auth.signInWithCredential(credential)
                .addOnSuccessListener { continuation.resume(Unit) }
                .addOnFailureListener { continuation.resumeWithException(it) }
        }
    }

    fun isUserAlreadyAuthorized() = Firebase.auth.currentUser != null

    fun onResult(authResultData: AuthResultData) {
        authMethod?.onResult(authResultData)
    }
}

