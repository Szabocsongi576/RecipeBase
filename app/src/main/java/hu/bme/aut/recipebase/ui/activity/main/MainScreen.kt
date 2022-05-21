package hu.bme.aut.recipebase.ui.activity.main

import android.content.Intent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import hu.bme.aut.recipebase.ui.activity.recipe_details.RecipeDetailsActivity
import hu.bme.aut.recipebase.ui.components.BottomLoading
import hu.bme.aut.recipebase.ui.components.Loading
import hu.bme.aut.recipebase.ui.components.RecipeList
import hu.bme.aut.recipebase.ui.components.app_bar.MainAppBar
import hu.bme.aut.recipebase.ui.dialog.add_recipe.AddRecipeDialog
import hu.bme.aut.recipebase.ui.dialog.add_recipe.AddRecipeDialogViewModel
import hu.bme.aut.recipebase.ui.dialog.edit_recipe.EditRecipeDialog
import hu.bme.aut.recipebase.ui.dialog.edit_recipe.EditRecipeDialogViewModel
import hu.bme.aut.recipebase.ui.state.SearchWidgetState
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
    val fetchLoadingState by mainViewModel.fetchLoadingState
    val deleteLoadingState by mainViewModel.deleteLoadingState

    val context = LocalContext.current

    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    val listState = rememberLazyListState()
    val openAddRecipeDialog = remember { mutableStateOf(false) }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
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
                }
            )
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
        RecipeList(
            recipes = recipeListState,
            indexOfFetchTriggerState = mainViewModel.indexOfFetchTriggerState,
            enableFetchTriggerState = mainViewModel.enableFetchTriggerState,
            listState = listState,
            onFetch = {
                mainViewModel.fetchRecipes(
                    onError = {
                        coroutineScope.launch {
                            scaffoldState.snackbarHostState.showSnackbar(
                                message = it,
                            )
                        }
                    }
                )
            },
            onItemClick = {
                val intent = Intent(context, RecipeDetailsActivity::class.java)
                intent.putExtra("recipe_id", it.toLong())
                val id = it
                val recipe = mainViewModel.recipeListState.value.first { r -> r.id == id }

                intent.putExtra("name", recipe.name)
                intent.putExtra("image", recipe.thumbnailUrl)

                val components = ArrayList<String>()
                recipe.getSections()!!.first()
                    .getComponents()!!.forEach { c -> components.add(c.rawText!!) }
                intent.putStringArrayListExtra("components", components)

                val instructions = ArrayList<String>()
                recipe.getInstructions()!!.forEach { i -> instructions.add(i.displayText!!) }
                intent.putStringArrayListExtra("instructions", instructions)

                val nutrition = recipe.nutrition!!
                nutrition.sugar?.let { it1 -> intent.putExtra("sugar", it1.toLong()) }
                nutrition.fat?.let { it1 -> intent.putExtra("fat", it1.toLong()) }
                nutrition.protein?.let { it1 -> intent.putExtra("protein", it1.toLong()) }
                nutrition.fiber?.let { it1 -> intent.putExtra("fiber", it1.toLong()) }
                nutrition.carbohydrates?.let { it1 -> intent.putExtra("carbohydrates", it1.toLong()) }
                nutrition.calories?.let { it1 -> intent.putExtra("calories", it1.toLong()) }

                context.startActivity(intent)
            },
            onItemDeleteClick = {
                mainViewModel.deleteRecipe(
                    id = it,
                    onError = {
                        coroutineScope.launch {
                            scaffoldState.snackbarHostState.showSnackbar(
                                message = it,
                            )
                        }
                    }
                )
            },
            onItemEditClicked = {
                mainViewModel.setSelectedRecipeState(it)
            },
        )

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
            visible = deleteLoadingState,
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