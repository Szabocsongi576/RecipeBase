package hu.bme.aut.recipebase.ui.activity.recipe_details

import android.app.Application
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.recipebase.network.model.Recipe
import javax.inject.Inject

@HiltViewModel
class RecipeDetailsViewModel @Inject constructor(
    application: Application,
    private val repository: RecipeDetailsRepository
) : AndroidViewModel(application) {
    private var isOwn: Boolean? = null

    private val _recipeState: MutableState<Recipe?> = mutableStateOf(value = null)
    val recipeState: State<Recipe?> = _recipeState

    fun setRecipe(recipe: Recipe) {
        _recipeState.value = recipe

        isOwn = recipe.id!!.toLong() > 100000
    }
}