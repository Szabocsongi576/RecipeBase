package hu.bme.aut.recipebase.ui.recipe_details

import hu.bme.aut.recipebase.persistence.RecipeDao
import javax.inject.Inject

class RecipeDetailsRepository {
    @Inject
    lateinit var recipeDao: RecipeDao

}