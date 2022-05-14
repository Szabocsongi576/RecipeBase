package hu.bme.aut.recipebase.ui.activity.main

import android.app.Application
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
        var FROM: Long = 0
    }

    private val _uiState = MutableStateFlow<UiState>(UiState.Empty)
    val uiState: StateFlow<UiState> = _uiState

    private val _searchWidgetState: MutableState<SearchWidgetState> =
        mutableStateOf(value = SearchWidgetState.CLOSED)
    val searchWidgetState: State<SearchWidgetState> = _searchWidgetState

    private val _searchTextState: MutableState<String> = mutableStateOf(value = "")
    val searchTextState: State<String> = _searchTextState

    private val _recipeListState: MutableState<List<Recipe>> = mutableStateOf(listOf())
    val recipeListState: State<List<Recipe>> = _recipeListState

    private val _indexOfFetchTriggerState: MutableState<Long> = mutableStateOf(value = FETCH_SIZE)
    val indexOfFetchTriggerState: State<Long> = _indexOfFetchTriggerState

    private val _fetchState: MutableState<Boolean> = mutableStateOf(value = false)
    val fetchState: State<Boolean> = _fetchState

    init {
        _uiState.value = UiState.Loading
        fetchRecipes()
    }

    fun updateSearchWidgetState(newValue: SearchWidgetState) {
        _searchWidgetState.value = newValue
    }

    fun updateSearchTextState(newValue: String) {
        _searchTextState.value = newValue
    }

    fun fetchRecipes() {
        viewModelScope.launch {
            _fetchState.value = true

            try {
                val response = repository.fetchRecipes(
                    query = searchTextState.value,
                    from = BigDecimal.valueOf(FROM),
                    size = BigDecimal.valueOf(FETCH_SIZE),
                )

                val currentList: List<Recipe> = recipeListState.value
                if (currentList.isEmpty()) {
                    _uiState.value = UiState.Loaded
                }
                _recipeListState.value = currentList.plus(response!!.getResults()!!)
                FROM += FETCH_SIZE
                _indexOfFetchTriggerState.value = FROM - (FETCH_SIZE / 2)
            } catch (e: Exception) {
                onErrorOccurred(message = e.message.toString())
            }

            _fetchState.value = false
        }
    }

    private fun onErrorOccurred(message: String) {
        _uiState.value = UiState.Error(message)
    }
}