package hu.bme.aut.recipebase

import io.kotest.core.listeners.TestListener
import io.kotest.core.spec.Spec
import io.kotest.core.test.TestCase
import io.kotest.core.test.TestResult

class ViewModelTestListener : TestListener {

    override suspend fun beforeTest(testCase: TestCase) {

    }

    override suspend fun afterTest(testCase: TestCase, result: TestResult) {

    }

    override suspend fun beforeSpec(spec: Spec) {

    }

    override suspend fun afterSpec(spec: Spec) {

    }
}

