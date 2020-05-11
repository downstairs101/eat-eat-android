package com.downstairs.eatat.core

import androidx.arch.core.executor.ArchTaskExecutor
import androidx.arch.core.executor.TaskExecutor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.ExternalResource

class InstantTaskRule : ExternalResource() {

    private val testDispatcher = TestCoroutineDispatcher()

    override fun before() {

        Dispatchers.setMain(testDispatcher)

        ArchTaskExecutor.getInstance().setDelegate(object : TaskExecutor() {
            override fun executeOnDiskIO(runnable: Runnable) = runnable.run()

            override fun isMainThread() = true

            override fun postToMainThread(runnable: Runnable) = runnable.run()

        })
    }

    override fun after() {
        Dispatchers.resetMain()

        ArchTaskExecutor.getInstance().setDelegate(null)
    }
}