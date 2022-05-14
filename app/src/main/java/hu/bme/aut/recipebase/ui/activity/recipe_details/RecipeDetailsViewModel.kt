package hu.bme.aut.recipebase.ui.activity.recipe_details

import android.app.Application
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.recipebase.network.model.Recipe
import hu.bme.aut.recipebase.ui.state.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeDetailsViewModel @Inject constructor(
    application: Application,
    private val repository: RecipeDetailsRepository
) : AndroidViewModel(application) {

    private val _uiState = MutableStateFlow<UiState>(UiState.Empty)
    val uiState: StateFlow<UiState> = _uiState

    private val _recipeState: MutableState<Recipe?> = mutableStateOf(value = null)
    val recipeState: State<Recipe?> = _recipeState

    init {
        _uiState.value = UiState.Loading
    }

    fun initRecipe(recipeId: Long) {
        viewModelScope.launch {
            try {
                val response = repository.fetchRecipe(
                    id = recipeId,
                )

                _uiState.value = UiState.Loaded
                _recipeState.value = response
            } catch (e: Exception) {
                onErrorOccurred(message = e.message.toString())
            }
        }
    }

    private fun onErrorOccurred(message: String) {
        _uiState.value = UiState.Error(message)
    }
}