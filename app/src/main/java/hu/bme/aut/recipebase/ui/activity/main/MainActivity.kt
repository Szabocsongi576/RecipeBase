package hu.bme.aut.recipebase.ui.activity.main

import android.R.id
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.google.firebase.analytics.FirebaseAnalytics
import dagger.hilt.android.AndroidEntryPoint
import hu.bme.aut.recipebase.ui.components.app_bar.DefaultAppBar
import hu.bme.aut.recipebase.ui.components.screen.ErrorScreen
import hu.bme.aut.recipebase.ui.components.screen.LoadingScreen
import hu.bme.aut.recipebase.ui.components.screen.NoDataScreen
import hu.bme.aut.recipebase.ui.dialog.add_recipe.AddRecipeDialogViewModel
import hu.bme.aut.recipebase.ui.dialog.edit_recipe.EditRecipeDialogViewModel
import hu.bme.aut.recipebase.ui.state.UiState
import hu.bme.aut.recipebase.ui.theme.RecipeBaseTheme


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val mainViewModel: MainViewModel by viewModels()
    private val addRecipeDialogViewModel: AddRecipeDialogViewModel by viewModels()
    private val editRecipeDialogViewModel: EditRecipeDialogViewModel by viewModels()

    private lateinit var mFirebaseAnalytics: FirebaseAnalytics

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this)

        val bundle = Bundle()
        bundle.putString(FirebaseAnalytics.Param.CONTENT, "Test event")
        mFirebaseAnalytics.logEvent(
            "my_event",
            bundle
        )

        setContent {
            RecipeBaseTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val defaultAppBar: @Composable (() -> Unit) = {
                        DefaultAppBar(
                            title = "Recipes"
                        )
                    }

                    when (val state = mainViewModel.uiState.collectAsState().value) {
                        is UiState.Empty -> {
                            NoDataScreen()
                        }
                        is UiState.Loading -> {
                            LoadingScreen(
                                topBar = defaultAppBar
                            )
                        }
                        is UiState.Error -> {
                            ErrorScreen(
                                message = state.message,
                                topBar = defaultAppBar
                            )
                        }
                        is UiState.Loaded -> {
                            MainScreen(
                                mainViewModel = mainViewModel,
                                addRecipeDialogViewModel = addRecipeDialogViewModel,
                                editRecipeDialogViewModel = editRecipeDialogViewModel,
                            )
                        }
                    }
                }
            }
        }
    }
}