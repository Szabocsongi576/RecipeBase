package hu.bme.aut.recipebase.network

import hu.bme.aut.recipebase.createNetworkRecipe
import hu.bme.aut.recipebase.network.api.RecipesApi
import hu.bme.aut.recipebase.ui.dialog.edit_recipe.EditRecipeDialogRepository
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.*

class ApiAndEditRecipeDialogRepositoryTest: BehaviorSpec({
    val recipesApi: RecipesApi = mockk()
    val editRecipeDialogRepository = EditRecipeDialogRepository(
        recipeDao = mockk(),
        recipesApi = recipesApi
    )

    Given("no preconditions") {
        coEvery { recipesApi.updateRecipe(id = 1, createNetworkRecipe(id = 1, name = "Test")) } returns Unit

        When("editRecipe is called") {
            editRecipeDialogRepository.editRecipe(createNetworkRecipe(id = 1, name = "Test"))

            Then("updateRecipe is called with correct parameter") {
                coVerify { recipesApi.updateRecipe(id = 1, createNetworkRecipe(id = 1, name = "Test")) }
            }
        }
    }
})