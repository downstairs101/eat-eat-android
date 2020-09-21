package com.downstairs.tosplit.splash

import com.downstairs.core.auth.FirebaseClient
import com.squareup.moshi.Json
import retrofit2.http.GET
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

class UserRepository @Inject constructor(private val userService: UserService) {
    suspend fun getUser(): User {
        return userService.getUser().toDomain()
    }
}

data class User(val name: String)

interface UserService {

    @GET("/user")
    suspend fun getUser(): UserRemote
}

data class UserRemote(
    @Json(name = "name") val name: String
) {
    fun toDomain() = User(name)
}

sealed class UserStatus {
    object Authorized : UserStatus()
    object Unauthorized : UserStatus()
    object Incomplete : UserStatus()
}