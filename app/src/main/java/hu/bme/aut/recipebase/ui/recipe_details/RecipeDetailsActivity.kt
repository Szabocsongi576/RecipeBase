package hu.bme.aut.recipebase.ui.recipe_details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import hu.bme.aut.recipebase.R

class RecipeDetailsActivity : AppCompatActivity() {
    private val recipeDetailsViewModel: RecipeDetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_details)
    }
}