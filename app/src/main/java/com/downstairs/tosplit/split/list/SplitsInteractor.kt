package com.downstairs.tosplit.split.list

import com.downstairs.tosplit.split.Split
import com.downstairs.tosplit.split.data.SplitServiceApi
import javax.inject.Inject

class SplitsInteractor @Inject constructor(private val splitService: SplitServiceApi) {

    suspend fun fetchSpits(): Result<List<Split>> {
        return try {
            val splits = splitService.fetchSplits().map { it.toDomain() }
            Result.success(splits)
        } catch (error: Throwable) {
            Result.failure(error)
        }
    }
}