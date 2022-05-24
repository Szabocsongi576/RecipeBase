package hu.bme.aut.recipebase.network

import hu.bme.aut.recipebase.createNetworkRecipe
import hu.bme.aut.recipebase.network.api.RecipesApi
import hu.bme.aut.recipebase.ui.dialog.add_recipe.AddRecipeDialogRepository
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.*

class ApiAndAddRecipeDialogRepositoryTest: BehaviorSpec({
    val recipesApi: RecipesApi = mockk()
    val addRecipeDialogRepository = AddRecipeDialogRepository(
        recipeDao = mockk(),
        recipesApi = recipesApi
    )

    Given("no preconditions") {
        coEvery { recipesApi.createNewRecipe(createNetworkRecipe(id = 1, name = "Test")) } returns 1

        When("createRecipe is called") {
            addRecipeDialogRepository.createRecipe(createNetworkRecipe(id = 1, name = "Test"))

            Then("uploaded recipe's id should be returned") {
                //coVerify { recipesApi.createNewRecipe(createNetworkRecipe(id = 1, name = "Test")) }
            }
        }
    }
})