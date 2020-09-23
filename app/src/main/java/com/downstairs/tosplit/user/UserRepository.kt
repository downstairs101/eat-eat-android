package com.downstairs.tosplit.user

import javax.inject.Inject

class UserRepository @Inject constructor(private val userService: UserService) {

    suspend fun getUser(): User {
        return userService.getUser().toDomain()
    }
}