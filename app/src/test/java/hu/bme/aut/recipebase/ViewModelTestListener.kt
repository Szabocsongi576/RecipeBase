package hu.bme.aut.recipebase

import androidx.arch.core.executor.ArchTaskExecutor
import androidx.arch.core.executor.TaskExecutor
import io.kotest.core.listeners.TestListener
import io.kotest.core.spec.Spec
import io.kotest.core.test.TestCase
import io.kotest.core.test.TestResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain

@ExperimentalCoroutinesApi
class ViewModelTestListener : TestListener {

    private val testDispatcher = UnconfinedTestDispatcher()

    override suspend fun beforeTest(testCase: TestCase) {
        Dispatchers.setMain(testDispatcher)
    }

    override suspend fun afterTest(testCase: TestCase, result: TestResult) {
        Dispatchers.resetMain()
    }

    override suspend fun beforeSpec(spec: Spec) {
        ArchTaskExecutor.getInstance().setDelegate(object : TaskExecutor() {
            override fun executeOnDiskIO(runnable: Runnable) {
                runnable.run()
            }

            override fun postToMainThread(runnable: Runnable) {
                runnable.run()
            }

            override fun isMainThread(): Boolean {
                return true
            }
        })
    }

    override suspend fun afterSpec(spec: Spec) {
        ArchTaskExecutor.getInstance().setDelegate(null)
    }
}

