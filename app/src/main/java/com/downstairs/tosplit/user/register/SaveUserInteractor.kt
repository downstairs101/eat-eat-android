package com.downstairs.tosplit.user.register

import com.downstairs.core.tools.Completable
import com.downstairs.tosplit.user.data.User
import com.downstairs.tosplit.user.data.UserRepository
import javax.inject.Inject

class SaveUserInteractor @Inject constructor(private val userRepository: UserRepository) {

    suspend fun save(user: User): Completable {
        return try {
            userRepository.saveUser(user)

            Completable.Complete
        } catch (error: Throwable) {
            Completable.Failure(error)
        }
    }
}
