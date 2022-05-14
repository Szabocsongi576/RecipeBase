package hu.bme.aut.recipebase.ui.activity.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import hu.bme.aut.recipebase.ui.components.ErrorScreen
import hu.bme.aut.recipebase.ui.components.LoadingScreen
import hu.bme.aut.recipebase.ui.components.NoDataScreen
import hu.bme.aut.recipebase.ui.state.UiState
import hu.bme.aut.recipebase.ui.theme.RecipeBaseTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RecipeBaseTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    when (val state = mainViewModel.uiState.collectAsState().value) {
                        is UiState.Empty -> NoDataScreen()
                        is UiState.Loading -> LoadingScreen()
                        is UiState.Error -> ErrorScreen(state.message)
                        is UiState.Loaded -> MainScreen(mainViewModel)
                    }
                }
            }
        }
    }
}