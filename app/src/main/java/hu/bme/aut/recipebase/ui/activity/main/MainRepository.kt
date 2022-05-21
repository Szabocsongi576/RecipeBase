package hu.bme.aut.recipebase.ui.activity.main

import hu.bme.aut.recipebase.network.api.RecipesApi
import hu.bme.aut.recipebase.network.model.RecipeList
import hu.bme.aut.recipebase.persistence.RecipeDao
import kotlinx.coroutines.delay
import java.math.BigDecimal
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRepository @Inject constructor(
    private val recipesApi: RecipesApi,
    private val recipeDao: RecipeDao,
) {

    suspend fun fetchRecipes(query: String?, from: BigDecimal, size: BigDecimal): RecipeList? {
        return recipesApi.getAllRecipes(
            from = from,
            size = size,
            tags = "under_30_minutes",
            q = query,
        )
    }

    suspend fun deleteRecipe(id: BigDecimal) {
        delay(1000)
    }
}