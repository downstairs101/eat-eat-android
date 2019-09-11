package com.downstairs

import androidx.arch.core.executor.ArchTaskExecutor
import androidx.arch.core.executor.TaskExecutor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.extension.AfterAllCallback
import org.junit.jupiter.api.extension.BeforeEachCallback
import org.junit.jupiter.api.extension.ExtensionContext

class InstantTaskExtension : BeforeEachCallback, AfterAllCallback {
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    override fun beforeEach(context: ExtensionContext?) {
        Dispatchers.setMain(mainThreadSurrogate)

        ArchTaskExecutor.getInstance().setDelegate(object : TaskExecutor() {
            override fun executeOnDiskIO(runnable: Runnable) = runnable.run()

            override fun isMainThread() = true

            override fun postToMainThread(runnable: Runnable) = runnable.run()

        })

    }

    override fun afterAll(context: ExtensionContext?) {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()

        ArchTaskExecutor.getInstance().setDelegate(null)
    }

}