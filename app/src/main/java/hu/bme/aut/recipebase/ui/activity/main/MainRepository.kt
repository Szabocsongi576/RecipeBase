package hu.bme.aut.recipebase.ui.activity.main

import hu.bme.aut.recipebase.network.api.RecipesApi
import hu.bme.aut.recipebase.persistence.RecipeDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRepository @Inject constructor(
    private val recipesApi: RecipesApi,
    private val recipeDao: RecipeDao,
) {
}