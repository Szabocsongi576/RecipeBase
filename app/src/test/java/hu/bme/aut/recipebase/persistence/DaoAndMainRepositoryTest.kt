package hu.bme.aut.recipebase.persistence

import com.google.common.truth.Truth.assertThat
import hu.bme.aut.recipebase.createDbRecipe
import hu.bme.aut.recipebase.createNetworkRecipe
import hu.bme.aut.recipebase.ui.activity.main.MainRepository
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify

class DaoAndMainRepositoryTest: BehaviorSpec({
    val recipeDao: RecipeDao = mockk()
    val mainRepository = MainRepository(
        recipeDao = recipeDao,
        recipesApi = mockk()
    )

    Given("a single recipe with id=100 saved to the database") {
        every { recipeDao.getAll() } returns listOf(createDbRecipe(id = 100))

        When("readAllFavorite is called") {
            val result = mainRepository.readAllFavorite()

            Then("recipe with id=100 should be returned") {
                assertThat(result).containsExactly(createNetworkRecipe(id = 100))
            }
        }
    }

    Given("no preconditions") {
        every { recipeDao.insertOrUpdate(createDbRecipe(id = 1, name = "Test")) } returns Unit
        every { recipeDao.delete(createDbRecipe(id = 1)) } returns Unit

        When("writeFavorite is called") {
            mainRepository.writeFavorite(createNetworkRecipe(id = 1, name = "Test"))

            Then("saved recipe returned as Unit") {
                verify { recipeDao.insertOrUpdate(createDbRecipe(id = 1, name = "Test")) }
            }
        }
        When("deleteFavorite is called") {
            mainRepository.deleteFavorite(createNetworkRecipe(id = 1, name = "Test"))

            Then("delete is called with correct parameter") {
                verify { recipeDao.delete(createDbRecipe(id = 1, name = "Test")) }
            }
        }
    }
})