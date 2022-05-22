package hu.bme.aut.recipebase.ui.dialog.edit_recipe

import hu.bme.aut.recipebase.network.api.RecipesApi
import hu.bme.aut.recipebase.network.model.Recipe
import hu.bme.aut.recipebase.persistence.RecipeDao
import kotlinx.coroutines.delay
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EditRecipeDialogRepository @Inject constructor(
    private val recipesApi: RecipesApi,
    private val recipeDao: RecipeDao,
) {
    suspend fun editRecipe(recipe: Recipe) {
        delay(1000)
    }
}