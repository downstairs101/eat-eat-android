package com.downstairs.tosplit.splash

import com.downstairs.core.auth.FirebaseClient
import com.downstairs.tosplit.user.UserRepository
import javax.inject.Inject

class SplashInteractor @Inject constructor(
    private val firebaseClient: FirebaseClient,
    private val userRepository: UserRepository
) {

    suspend fun checkUserStatus(): UserStatus {
        return if (firebaseClient.isUserUnauthorized()) {
            UserStatus.Unauthorized
        } else {
            fetchUserStatus()
        }
    }

    private suspend fun fetchUserStatus(): UserStatus {
        return try {
            userRepository.getUser()
            UserStatus.Authorized
        } catch (error: Throwable) {
            UserStatus.Incomplete
        }
    }
}

