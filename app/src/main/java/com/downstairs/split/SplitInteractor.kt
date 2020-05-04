package com.downstairs.split

import com.downstairs.split.data.SplitServiceApi
import javax.inject.Inject

class SplitInteractor @Inject constructor(private val splitService: SplitServiceApi) {

    suspend fun fetchSpits(userId: Int): Result<List<Split>> {
        return try {
            val splits = splitService.fetchSplits(userId).map { it.toDomain() }
            Result.success(splits)
        } catch (error: Throwable) {
            Result.failure(error)
        }
    }
}