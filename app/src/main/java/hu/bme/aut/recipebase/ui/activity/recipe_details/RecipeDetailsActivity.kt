package hu.bme.aut.recipebase.ui.activity.recipe_details

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import hu.bme.aut.recipebase.ui.components.ErrorScreen
import hu.bme.aut.recipebase.ui.components.LoadingScreen
import hu.bme.aut.recipebase.ui.components.NoDataScreen
import hu.bme.aut.recipebase.ui.state.UiState
import hu.bme.aut.recipebase.ui.theme.RecipeBaseTheme

@AndroidEntryPoint
class RecipeDetailsActivity : ComponentActivity() {
    private val recipeDetailsViewModel: RecipeDetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val recipeId = intent.getLongExtra("recipe_id", -1)
        recipeDetailsViewModel.initRecipe(recipeId)

        setContent {
            RecipeBaseTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    when (val state = recipeDetailsViewModel.uiState.collectAsState().value) {
                        is UiState.Empty -> NoDataScreen()
                        is UiState.Loading -> LoadingScreen()
                        is UiState.Error -> ErrorScreen(state.message)
                        is UiState.Loaded -> RecipeDetailsScreen(recipeDetailsViewModel)
                    }
                }
            }
        }
    }
}