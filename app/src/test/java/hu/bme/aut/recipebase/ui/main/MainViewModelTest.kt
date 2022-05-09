package hu.bme.aut.recipebase.ui.main

import hu.bme.aut.recipebase.ViewModelTestListener
import io.kotest.core.spec.style.StringSpec
import io.kotest.core.test.TestCase
import io.mockk.clearAllMocks
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class MainViewModelTest : StringSpec({

}) {
    init {
        listener(ViewModelTestListener())
    }

    override fun beforeTest(testCase: TestCase) {
        super.beforeTest(testCase)
        clearAllMocks()
    }
}
