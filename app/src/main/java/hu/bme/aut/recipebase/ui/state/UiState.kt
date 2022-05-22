package hu.bme.aut.recipebase.ui.state

sealed class UiState {
    object Empty : UiState()
    object Loading : UiState()
    object Loaded : UiState()
    class Error(val message: String) : UiState()
}