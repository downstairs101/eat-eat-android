package com.downstairs.tosplit.user

import javax.inject.Inject

class UserRepository @Inject constructor(private val userService: UserServiceApi) {

    suspend fun getUser(): User {
        try {
            return userService.getUser().toDomain()
        } catch (error: Throwable) {
            if (error is KotlinNullPointerException) throw UserNotFoundException()
            else throw error
        }
    }
}