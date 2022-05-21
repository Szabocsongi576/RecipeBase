package hu.bme.aut.recipebase.ui.activity.main

import android.app.Application
import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.recipebase.network.model.Recipe
import hu.bme.aut.recipebase.ui.state.UiState
import hu.bme.aut.recipebase.ui.state.SearchWidgetState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.math.BigDecimal
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    application: Application,
    private val repository: MainRepository
) :
    AndroidViewModel(application) {

    companion object {
        const val FETCH_SIZE: Long = 20
    }

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    private val _selectedRecipeState: MutableState<Recipe?> = mutableStateOf(value = null)
    private val _searchWidgetState: MutableState<SearchWidgetState> = mutableStateOf(value = SearchWidgetState.CLOSED)
    private val _searchTextState: MutableState<String> = mutableStateOf(value = "")
    private val _recipeListState: MutableState<List<Recipe>> = mutableStateOf(listOf())
    private val _indexOfFetchTriggerState: MutableState<Long> = mutableStateOf(value = FETCH_SIZE)
    private val _enableFetchTriggerState: MutableState<Boolean> = mutableStateOf(value = true)
    private val _fetchFromState: MutableState<Long> = mutableStateOf(value = 0)
    private val _fetchLoadingState: MutableState<Boolean> = mutableStateOf(value = false)
    private val _deleteLoadingState: MutableState<Boolean> = mutableStateOf(value = false)

    val uiState: StateFlow<UiState> = _uiState
    val selectedRecipeState: State<Recipe?> = _selectedRecipeState
    val searchWidgetState: State<SearchWidgetState> = _searchWidgetState
    val searchTextState: State<String> = _searchTextState
    val recipeListState: State<List<Recipe>> = _recipeListState
    val indexOfFetchTriggerState: State<Long> = _indexOfFetchTriggerState
    val enableFetchTriggerState: MutableState<Boolean> = _enableFetchTriggerState
    val fetchLoadingState: State<Boolean> = _fetchLoadingState
    val deleteLoadingState: State<Boolean> = _deleteLoadingState

    init {
        initFetchRecipes()
    }

    fun setSelectedRecipeState(newValue: Recipe?) {
        _selectedRecipeState.value = newValue
    }

    fun updateSearchWidgetState(newValue: SearchWidgetState) {
        _searchWidgetState.value = newValue
    }

    fun updateSearchTextState(newValue: String) {
        _searchTextState.value = newValue
    }

    private fun initFetchRecipes(
        from: Long = _fetchFromState.value,
        size: Long = FETCH_SIZE,
    ) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading

            try {
                val response = repository.fetchRecipes(
                    query = searchTextState.value,
                    from = BigDecimal.valueOf(from),
                    size = BigDecimal.valueOf(size),
                )

                val currentList: List<Recipe> = _recipeListState.value
                _recipeListState.value = currentList.plus(response!!.getResults()!!)

                onRecipesFetched()
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message.toString())
            }

            _uiState.value = UiState.Loaded
        }
    }

    fun fetchRecipes(
        onError: (message: String) -> Unit,
    ) {
        viewModelScope.launch {
            _fetchLoadingState.value = true

            try {
                val response = repository.fetchRecipes(
                    query = searchTextState.value,
                    from = BigDecimal.valueOf(_fetchFromState.value),
                    size = BigDecimal.valueOf(FETCH_SIZE),
                )

                val currentList: List<Recipe> = _recipeListState.value
                _recipeListState.value = currentList.plus(response!!.getResults()!!)

                onRecipesFetched()
            } catch (e: Exception) {
                onError(e.message.toString())
            }

            _fetchLoadingState.value = false
        }
    }

    fun searchRecipes(
        onError: (message: String) -> Unit,
    ) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading

            val prevFetchFrom = _fetchFromState.value
            val prevRecipeList = _recipeListState.value
            _fetchFromState.value = 0
            _recipeListState.value = listOf()

            try {
                val response = repository.fetchRecipes(
                    query = searchTextState.value,
                    from = BigDecimal.valueOf(_fetchFromState.value),
                    size = BigDecimal.valueOf(FETCH_SIZE),
                )

                val currentList: List<Recipe> = _recipeListState.value
                _recipeListState.value = currentList.plus(response!!.getResults()!!)

                onRecipesFetched()
            } catch (e: Exception) {
                _fetchFromState.value = prevFetchFrom
                _recipeListState.value = prevRecipeList
                onError(e.message.toString())
            }

            _uiState.value = UiState.Loaded
        }
    }

    fun deleteRecipe(
        id: BigDecimal,
        onError: (message: String) -> Unit,
    ) {
        viewModelScope.launch {
            _deleteLoadingState.value = true

            try {
                repository.deleteRecipe(
                    id = id,
                )
                val currentList: List<Recipe> = recipeListState.value
                _recipeListState.value = currentList.filter { it.id != id }

                onRecipeDeleted()
            } catch (e: Exception) {
                onError(e.message.toString())
            }

            _deleteLoadingState.value = false
        }
    }

    private fun onRecipesFetched() {
        _fetchFromState.value += FETCH_SIZE
        _indexOfFetchTriggerState.value = _recipeListState.value.size - (FETCH_SIZE / 2)
        if(_fetchFromState.value >= 60) {
            enableFetchTriggerState.value = false
        }
    }

    private fun onRecipeDeleted() {
        val currentListSize = recipeListState.value.size
        if (currentListSize < FETCH_SIZE / 2) {
            fetchRecipes(
                onError = {}
            )
        } else {
            _indexOfFetchTriggerState.value = _indexOfFetchTriggerState.value.minus(1)
        }
    }

    fun onRecipeCreated(recipe: Recipe) {
        _recipeListState.value = listOf(recipe).plus(_recipeListState.value)
        _indexOfFetchTriggerState.value += 1
    }

    fun onRecipeEdited(recipe: Recipe) {
        recipe.thumbnailUrl = _selectedRecipeState.value!!.thumbnailUrl
        _recipeListState.value = _recipeListState.value.map {
            if (it.id == _selectedRecipeState.value!!.id) {
                recipe
            } else {
                it
            }
        }
        _selectedRecipeState.value = null
    }
}