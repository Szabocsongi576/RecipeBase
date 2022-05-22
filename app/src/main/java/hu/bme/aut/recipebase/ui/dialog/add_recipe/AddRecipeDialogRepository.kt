package hu.bme.aut.recipebase.ui.dialog.add_recipe

import hu.bme.aut.recipebase.network.api.RecipesApi
import hu.bme.aut.recipebase.network.model.Recipe
import hu.bme.aut.recipebase.persistence.RecipeDao
import kotlinx.coroutines.delay
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AddRecipeDialogRepository @Inject constructor(
    private val recipesApi: RecipesApi,
    private val recipeDao: RecipeDao,
) {
    suspend fun createRecipe(recipe: Recipe) {
        delay(1000)
    }
}