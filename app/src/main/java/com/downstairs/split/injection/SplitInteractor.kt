package com.downstairs.split.injection

import com.downstairs.split.data.SplitServiceApi
import javax.inject.Inject

class SplitInteractor @Inject constructor(private val splitService: SplitServiceApi) {

    suspend fun fetchSpits(userId: Int): Result<String> {
        return try {
            Result.success(splitService.fetchSplits(userId).toDomain())
            Result.success("asdf")
        } catch (error: Throwable) {
            Result.failure(error)
        }
    }
}