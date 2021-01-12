package com.downstairs.tosplit.user.data

import com.downstairs.tosplit.user.UserNotFoundException
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