package hu.bme.aut.recipebase.ui.state

sealed class ErrorState {
    object Empty : ErrorState()
    class Error(val message: String) : ErrorState()
}