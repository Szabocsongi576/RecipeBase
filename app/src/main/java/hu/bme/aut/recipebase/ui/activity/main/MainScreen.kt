package hu.bme.aut.recipebase.ui.activity.main

import android.content.Intent
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import hu.bme.aut.recipebase.ui.activity.recipe_details.RecipeDetailsActivity
import hu.bme.aut.recipebase.ui.activity.recipe_details.RecipeDetailsScreen
import hu.bme.aut.recipebase.ui.activity.recipe_details.RecipeDetailsViewModel
import hu.bme.aut.recipebase.ui.components.BottomLoading
import hu.bme.aut.recipebase.ui.components.RecipeList
import hu.bme.aut.recipebase.ui.components.app_bar.MainAppBar
import hu.bme.aut.recipebase.ui.state.SearchWidgetState

@Composable
fun MainScreen(viewModel: MainViewModel) {
    val searchWidgetState by viewModel.searchWidgetState
    val searchTextState by viewModel.searchTextState

    val recipeListState by viewModel.recipeListState
    val fetchState by viewModel.fetchState

    val context = LocalContext.current

    Scaffold(
        topBar = {
            MainAppBar(
                searchWidgetState = searchWidgetState,
                searchTextState = searchTextState,
                onTextChange = {
                    viewModel.updateSearchTextState(newValue = it)
                },
                onCloseClicked = {
                    viewModel.updateSearchWidgetState(newValue = SearchWidgetState.CLOSED)
                },
                onSearchClicked = {
                    Log.d("Searched Text", it)
                },
                onSearchTriggered = {
                    viewModel.updateSearchWidgetState(newValue = SearchWidgetState.OPENED)
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { },
                content = {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = "Add Icon",
                        tint = Color.White
                    )
                },
            )
        },
    ) {
        RecipeList(
            recipes = recipeListState,
            indexOfFetchTriggerState = viewModel.indexOfFetchTriggerState,
            onFetch = {
                viewModel.fetchRecipes()
            },
            onItemClick = {
                val intent = Intent(context, RecipeDetailsActivity::class.java)
                intent.putExtra("recipe_id", it.toLong())
                context.startActivity(intent)
            },
        )

        AnimatedVisibility(visible = fetchState) {
            BottomLoading()
        }
    }
}