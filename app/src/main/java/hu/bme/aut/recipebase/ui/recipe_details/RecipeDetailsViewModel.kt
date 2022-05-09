package hu.bme.aut.recipebase.ui.recipe_details

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import hu.bme.aut.recipebase.persistence.AppDatabase

class RecipeDetailsViewModel(application: Application) : AndroidViewModel(application) {
    private val repository : RecipeDetailsRepository
    private var state = MutableLiveData<RecipeDetails>()

    init {
        val recipeDao = AppDatabase.getInstance(application).recipeDao()
        repository = RecipeDetailsRepository()
    }
}