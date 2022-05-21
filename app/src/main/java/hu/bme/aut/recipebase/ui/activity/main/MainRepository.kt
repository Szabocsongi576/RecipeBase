package hu.bme.aut.recipebase.ui.activity.main

import hu.bme.aut.recipebase.network.api.RecipesApi
import hu.bme.aut.recipebase.network.model.Recipe
import hu.bme.aut.recipebase.network.model.RecipeList
import hu.bme.aut.recipebase.persistence.RecipeDao
import hu.bme.aut.recipebase.persistence.model.DbRecipe
import kotlinx.coroutines.delay
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRepository @Inject constructor(
    private val recipesApi: RecipesApi,
    private val recipeDao: RecipeDao,
) {

    suspend fun fetchRecipes(query: String?, from: Long, size: Long): RecipeList? {
        return recipesApi.getAllRecipes(
            from = from,
            size = size,
            tags = "under_30_minutes",
            q = query,
        )
    }

    suspend fun deleteRecipe(id: Long) {
        delay(1000)
    }

    suspend fun readAllFavorite(): List<Recipe> {
        return recipeDao.getAll().map { it.toDomainObject() }.toList()
    }

    suspend fun writeFavorite(recipe: Recipe) {
        return recipeDao.insertOrUpdate(DbRecipe.fromDomainObject(recipe))
    }

    suspend fun deleteFavorite(recipe: Recipe) {
        return recipeDao.delete(DbRecipe.fromDomainObject(recipe))
    }
}