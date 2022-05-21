package hu.bme.aut.recipebase.ui.activity.main

import android.app.Application
import androidx.compose.runtime.*
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.recipebase.network.model.Recipe
import hu.bme.aut.recipebase.ui.state.UiState
import hu.bme.aut.recipebase.ui.state.SearchWidgetState
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
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
    private val _favoriteListState: MutableState<List<Recipe>> = mutableStateOf(listOf())
    private val _indexOfFetchTriggerState: MutableState<Long> = mutableStateOf(value = FETCH_SIZE)
    private val _fetchFromState: MutableState<Long> = mutableStateOf(value = 0)
    private val _fetchLoadingState: MutableState<Boolean> = mutableStateOf(value = false)
    private val _centerLoadingState: MutableState<Boolean> = mutableStateOf(value = false)
    private val _showOnlyFavoritesState: MutableState<Boolean> = mutableStateOf(value = false)

    val uiState: StateFlow<UiState> = _uiState
    val selectedRecipeState: State<Recipe?> = _selectedRecipeState
    val searchWidgetState: State<SearchWidgetState> = _searchWidgetState
    val searchTextState: State<String> = _searchTextState
    val recipeListState: State<List<Recipe>> = _recipeListState
    val favoriteListState: State<List<Recipe>> = _favoriteListState
    val indexOfFetchTriggerState: State<Long> = _indexOfFetchTriggerState
    val fetchFromState: State<Long> = _fetchFromState
    val fetchLoadingState: State<Boolean> = _fetchLoadingState
    val centerLoadingState: State<Boolean> = _centerLoadingState
    val showOnlyFavoritesState: State<Boolean> = _showOnlyFavoritesState

    init {
        viewModelScope.launch {
            try {
                val fetchResponse = repository.fetchRecipes(
                    query = searchTextState.value,
                    from = _fetchFromState.value,
                    size = FETCH_SIZE,
                )

                val currentList: List<Recipe> = _recipeListState.value
                _recipeListState.value = currentList.plus(fetchResponse!!.getResults()!!)

                onRecipesFetched()

                withContext(Dispatchers.IO) {
                    _favoriteListState.value = repository.readAllFavorite()
                }

                _uiState.value = UiState.Loaded
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message.toString())
            }
        }
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

    fun updateShowOnlyFavoritesState(newValue: Boolean) {
        _showOnlyFavoritesState.value = newValue
    }

    fun fetchRecipes(
        onError: (message: String) -> Unit,
    ) {
        viewModelScope.launch {
            _fetchLoadingState.value = true

            try {
                val response = repository.fetchRecipes(
                    query = searchTextState.value,
                    from = _fetchFromState.value,
                    size = FETCH_SIZE,
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
                    from = _fetchFromState.value,
                    size = FETCH_SIZE,
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
        recipe: Recipe,
        onError: (message: String) -> Unit,
    ) {
        viewModelScope.launch {
            _centerLoadingState.value = true

            try {
                repository.deleteRecipe(
                    id = recipe.id!!,
                )
                val currentList: List<Recipe> = recipeListState.value
                _recipeListState.value = currentList.filter { it.id != recipe.id!! }

                if(_favoriteListState.value.find { it.id == recipe.id } != null) {
                    withContext(Dispatchers.IO) {
                        repository.deleteFavorite(recipe)
                    }

                    val favorites: List<Recipe> = _favoriteListState.value
                    _favoriteListState.value = favorites.filter { it.id != recipe.id }
                }

                onRecipeDeleted()
            } catch (e: Exception) {
                onError(e.message.toString())
            }

            _centerLoadingState.value = false
        }
    }

    fun addToFavorite(
        recipe: Recipe,
        onError: (message: String) -> Unit,
    ) {
        viewModelScope.launch {
            _centerLoadingState.value = true

            try {
                withContext(Dispatchers.IO) {
                    repository.writeFavorite(recipe)
                }

                val favorites: List<Recipe> = _favoriteListState.value
                _favoriteListState.value = favorites.plus(recipe)
            } catch (e: Exception) {
                onError(e.message.toString())
            }

            _centerLoadingState.value = false
        }
    }

    fun deleteFromFavorite(
        recipe: Recipe,
        onError: (message: String) -> Unit,
    ) {
        viewModelScope.launch {
            _centerLoadingState.value = true

            try {
                withContext(Dispatchers.IO) {
                    repository.deleteFavorite(recipe)
                }

                val favorites: List<Recipe> = _favoriteListState.value
                _favoriteListState.value = favorites.filter { it.id != recipe.id }
            } catch (e: Exception) {
                onError(e.message.toString())
            }

            _centerLoadingState.value = false
        }
    }

    private fun onRecipesFetched() {
        _fetchFromState.value += FETCH_SIZE
        _indexOfFetchTriggerState.value = _recipeListState.value.size - (FETCH_SIZE / 2)
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