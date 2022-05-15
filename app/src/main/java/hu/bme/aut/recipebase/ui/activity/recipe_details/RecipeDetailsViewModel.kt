package hu.bme.aut.recipebase.ui.activity.recipe_details

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RecipeDetailsViewModel @Inject constructor(
    application: Application,
    private val repository: RecipeDetailsRepository
) : AndroidViewModel(application) {
}