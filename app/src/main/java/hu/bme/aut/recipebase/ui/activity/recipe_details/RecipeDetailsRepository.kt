package hu.bme.aut.recipebase.ui.activity.recipe_details

import hu.bme.aut.recipebase.network.api.RecipesApi
import hu.bme.aut.recipebase.network.model.Recipe
import hu.bme.aut.recipebase.persistence.RecipeDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RecipeDetailsRepository @Inject constructor(
    private val recipesApi: RecipesApi,
    private val recipeDao: RecipeDao,
) {

    suspend fun fetchRecipe(id: Long): Recipe? {
        return recipesApi.getRecipe(
            id = id,
        )
    }
}