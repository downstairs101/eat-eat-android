package com.downstairs

import androidx.arch.core.executor.ArchTaskExecutor
import androidx.arch.core.executor.TaskExecutor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.ExternalResource

class InstantTaskRule: ExternalResource() {
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    override fun before() {
        Dispatchers.setMain(mainThreadSurrogate)

        ArchTaskExecutor.getInstance().setDelegate(object : TaskExecutor() {
            override fun executeOnDiskIO(runnable: Runnable) = runnable.run()

            override fun isMainThread() = true

            override fun postToMainThread(runnable: Runnable) = runnable.run()

        })
    }

    override fun after() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()

        ArchTaskExecutor.getInstance().setDelegate(null)
    }
}