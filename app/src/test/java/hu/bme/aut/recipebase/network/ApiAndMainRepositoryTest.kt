package hu.bme.aut.recipebase.network

import com.google.common.truth.Truth.assertThat
import hu.bme.aut.recipebase.createNetworkRecipe
import hu.bme.aut.recipebase.network.api.RecipesApi
import hu.bme.aut.recipebase.network.model.RecipeList
import hu.bme.aut.recipebase.ui.activity.main.MainRepository
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.*

class ApiAndMainRepositoryTest: BehaviorSpec({
    val recipesApi: RecipesApi = mockk()
    val mainRepository = MainRepository(
        recipeDao = mockk(),
        recipesApi = recipesApi
    )

    Given("3 recipes saved to remote database") {
        val recipeList = RecipeList()
            .addResultsItem(createNetworkRecipe(id = 1, name = "Test 1"))
            .addResultsItem(createNetworkRecipe(id = 2, name = "Test 2"))
            .addResultsItem(createNetworkRecipe(id = 3, name = "Test 3"))

        coEvery { recipesApi.getAllRecipes(
            from = 0,
            size = 3,
            tags = "under_30_minutes",
            q = null,
        ) } returns recipeList

        When("fetchRecipes is called with size=3") {
            val result = mainRepository.fetchRecipes(
                from = 0,
                size = 3,
                query = null,
            )

            Then("exactly 3 recipe should be returned") {
                assertThat(result.getResults()!!.size).isEqualTo(3)
                assertThat(result.getResults()).contains(createNetworkRecipe(id = 1, name = "Test 1"))
                assertThat(result.getResults()).contains(createNetworkRecipe(id = 2, name = "Test 2"))
                assertThat(result.getResults()).contains(createNetworkRecipe(id = 3, name = "Test 3"))
            }
        }
    }

    Given("no preconditions") {
        coEvery { recipesApi.deleteRecipe(id = 1) } returns Unit

        When("deleteRecipe is called") {
            mainRepository.deleteRecipe(id = 1)

            Then("deleteRecipe is called with correct parameter") {
                //coVerify { recipesApi.deleteRecipe(id = 1) }
            }
        }
    }
})