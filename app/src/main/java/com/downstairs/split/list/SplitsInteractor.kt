package com.downstairs.split.list

import com.downstairs.split.Split
import com.downstairs.split.data.SplitServiceApi
import javax.inject.Inject

class SplitsInteractor @Inject constructor(private val splitService: SplitServiceApi) {

    suspend fun fetchSpits(userId: Int): Result<List<Split>> {
        return try {
            val splits = splitService.fetchSplits(userId).map { it.toDomain() }
            Result.success(splits)
        } catch (error: Throwable) {
            Result.failure(error)
        }
    }
}