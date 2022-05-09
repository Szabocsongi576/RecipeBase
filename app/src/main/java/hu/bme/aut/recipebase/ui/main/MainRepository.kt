package hu.bme.aut.recipebase.ui.main

import hu.bme.aut.recipebase.persistence.RecipeDao
import javax.inject.Inject

class MainRepository {
    @Inject lateinit var recipeDao: RecipeDao
}