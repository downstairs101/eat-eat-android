package com.downstairs.split.injection

import com.downstairs.split.Split
import com.downstairs.split.data.SplitServiceApi
import javax.inject.Inject

class SplitInteractor @Inject constructor(private val splitService: SplitServiceApi) {

    suspend fun fetchSpits(userId: Int): Result<Split> {
        return try {
            Result.success(splitService.fetchSplits(userId).toDomain())
        } catch (error: Throwable) {
            Result.failure(error)
        }
    }
}