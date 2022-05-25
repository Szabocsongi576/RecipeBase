package hu.bme.aut.recipebase.ui.activity.main

import android.content.Intent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.google.firebase.analytics.FirebaseAnalytics
import hu.bme.aut.recipebase.ui.activity.recipe_details.RecipeDetailsActivity
import hu.bme.aut.recipebase.ui.components.RecipeListItem
import hu.bme.aut.recipebase.ui.components.app_bar.DefaultAppBar
import hu.bme.aut.recipebase.ui.components.app_bar.MainAppBar
import hu.bme.aut.recipebase.ui.components.loading.BottomLoading
import hu.bme.aut.recipebase.ui.components.loading.Loading
import hu.bme.aut.recipebase.ui.dialog.add_recipe.AddRecipeDialog
import hu.bme.aut.recipebase.ui.dialog.add_recipe.AddRecipeDialogViewModel
import hu.bme.aut.recipebase.ui.dialog.edit_recipe.EditRecipeDialog
import hu.bme.aut.recipebase.ui.dialog.edit_recipe.EditRecipeDialogViewModel
import hu.bme.aut.recipebase.ui.state.SearchWidgetState
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

@Composable
fun MainScreen(
    mainViewModel: MainViewModel,
    addRecipeDialogViewModel: AddRecipeDialogViewModel,
    editRecipeDialogViewModel: EditRecipeDialogViewModel,
) {
    val searchWidgetState by mainViewModel.searchWidgetState
    val searchTextState by mainViewModel.searchTextState
    val recipeListState by mainViewModel.recipeListState
    val favoriteListState by mainViewModel.favoriteListState
    val indexOfFetchTriggerState by mainViewModel.indexOfFetchTriggerState
    val fetchFromState by mainViewModel.fetchFromState
    val fetchLoadingState by mainViewModel.fetchLoadingState
    val centerLoadingState by mainViewModel.centerLoadingState
    val showOnlyFavoritesState by mainViewModel.showOnlyFavoritesState

    val context = LocalContext.current

    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    val listState = rememberLazyListState()
    val openAddRecipeDialog = remember { mutableStateOf(false) }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            when (showOnlyFavoritesState) {
                true -> {
                    DefaultAppBar(
                        onBackClicked = {
                            mainViewModel.updateShowOnlyFavoritesState(false)
                        },
                        title = "Favorites"
                    )
                }
                false -> {
                    MainAppBar(
                        searchWidgetState = searchWidgetState,
                        searchTextState = searchTextState,
                        onTextChange = {
                            mainViewModel.updateSearchTextState(newValue = it)
                        },
                        onCloseClicked = {
                            mainViewModel.updateSearchWidgetState(newValue = SearchWidgetState.CLOSED)
                            mainViewModel.searchRecipes(
                                onError = {
                                    coroutineScope.launch {
                                        scaffoldState.snackbarHostState.showSnackbar(
                                            message = it,
                                        )
                                    }
                                }
                            )
                        },
                        onSearchClicked = {
                            mainViewModel.searchRecipes(
                                onError = {
                                    coroutineScope.launch {
                                        scaffoldState.snackbarHostState.showSnackbar(
                                            message = it,
                                        )
                                    }
                                }
                            )
                        },
                        onSearchTriggered = {
                            mainViewModel.updateSearchWidgetState(newValue = SearchWidgetState.OPENED)
                        },
                        onFavoritesClicked = {
                            mainViewModel.updateShowOnlyFavoritesState(true)
                        },
                    )
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    openAddRecipeDialog.value = true
                },
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

        val fetchCount: MutableState<Long> = remember { mutableStateOf(0) }

        LazyColumn(state = listState) {
            items(
                items = if (!showOnlyFavoritesState) recipeListState else favoriteListState,
                key = { recipe ->
                    recipe.id!!
                }
            ) { recipe ->
                val isFavorite = favoriteListState.find { it.id == recipe.id } != null

                RecipeListItem(
                    recipe = recipe,
                    isFavorite = isFavorite,
                    onClick = {
                        val intent = Intent(context, RecipeDetailsActivity::class.java)
                        intent.putExtra("recipe_id", recipe.id!!)

                        intent.putExtra("name", recipe.name)
                        intent.putExtra("image", recipe.thumbnailUrl)

                        val components = ArrayList<String>()
                        recipe.getSections()!!.first()
                            .getComponents()!!.forEach { c -> components.add(c.rawText!!) }
                        intent.putStringArrayListExtra("components", components)

                        val instructions = ArrayList<String>()
                        recipe.getInstructions()!!
                            .forEach { i -> instructions.add(i.displayText!!) }
                        intent.putStringArrayListExtra("instructions", instructions)

                        val nutrition = recipe.nutrition!!
                        nutrition.sugar?.let { it1 -> intent.putExtra("sugar", it1) }
                        nutrition.fat?.let { it1 -> intent.putExtra("fat", it1) }
                        nutrition.protein?.let { it1 -> intent.putExtra("protein", it1) }
                        nutrition.fiber?.let { it1 -> intent.putExtra("fiber", it1) }
                        nutrition.carbohydrates?.let { it1 ->
                            intent.putExtra(
                                "carbohydrates",
                                it1
                            )
                        }
                        nutrition.calories?.let { it1 -> intent.putExtra("calories", it1) }

                        context.startActivity(intent)
                    },
                    onDeleteClicked = {
                        mainViewModel.deleteRecipe(
                            recipe = recipe,
                            onError = {
                                coroutineScope.launch {
                                    scaffoldState.snackbarHostState.showSnackbar(
                                        message = it,
                                    )
                                }
                            }
                        )
                    },
                    onEditClicked = { mainViewModel.setSelectedRecipeState(recipe) },
                    onAddToFavoriteClicked = {
                        coroutineScope.launch {
                            if (isFavorite) {
                                mainViewModel.deleteFromFavorite(
                                    recipe = recipe,
                                    onError = {
                                        coroutineScope.launch {
                                            scaffoldState.snackbarHostState.showSnackbar(
                                                message = it,
                                            )
                                        }
                                    }
                                )
                            } else {
                                mainViewModel.addToFavorite(
                                    recipe = recipe,
                                    onError = {
                                        coroutineScope.launch {
                                            scaffoldState.snackbarHostState.showSnackbar(
                                                message = it,
                                            )
                                        }
                                    }
                                )
                            }
                        }
                    }
                )
            }
        }

        LaunchedEffect(listState) {
            snapshotFlow { listState.firstVisibleItemIndex }
                .map { index -> index > indexOfFetchTriggerState && fetchFromState <= 40 && !showOnlyFavoritesState }
                .distinctUntilChanged()
                .filter { it }
                .collect {
                    mainViewModel.fetchRecipes(
                        onError = {
                            coroutineScope.launch {
                                scaffoldState.snackbarHostState.showSnackbar(
                                    message = it,
                                )
                            }
                        }
                    )
                    fetchCount.value++
                }
        }

        if (openAddRecipeDialog.value) {
            AddRecipeDialog(
                onDismiss = {
                    openAddRecipeDialog.value = false
                },
                onSave = {
                    openAddRecipeDialog.value = false
                    mainViewModel.onRecipeCreated(it)
                    coroutineScope.launch {
                        listState.animateScrollToItem(0)
                    }
                },
                viewModel = addRecipeDialogViewModel,
            )
        }

        if (mainViewModel.selectedRecipeState.value != null) {
            editRecipeDialogViewModel.setupRecipe(mainViewModel.selectedRecipeState.value!!)
            EditRecipeDialog(
                onDismiss = {
                    mainViewModel.setSelectedRecipeState(null)
                },
                onEdit = {
                    mainViewModel.onRecipeEdited(it)
                },
                viewModel = editRecipeDialogViewModel,
            )
        }

        AnimatedVisibility(
            visible = centerLoadingState,
            enter = fadeIn(),
            exit = fadeOut(),
        ) {
            Loading()
        }

        AnimatedVisibility(
            visible = fetchLoadingState,
            enter = fadeIn(),
            exit = fadeOut(),
        ) {
            BottomLoading()
        }
    }
}