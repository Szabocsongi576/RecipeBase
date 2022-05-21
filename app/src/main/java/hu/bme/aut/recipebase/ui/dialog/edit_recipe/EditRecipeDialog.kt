package hu.bme.aut.recipebase.ui.dialog.edit_recipe

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import hu.bme.aut.recipebase.network.model.Recipe
import hu.bme.aut.recipebase.ui.components.ErrorDialog
import hu.bme.aut.recipebase.ui.components.loading.Loading
import hu.bme.aut.recipebase.ui.state.ErrorState
import hu.bme.aut.recipebase.ui.transformation.UnorderedListTransformation

@Composable
fun EditRecipeDialog(
    onDismiss: () -> Unit,
    onEdit: (recipe: Recipe) -> Unit,
    viewModel: EditRecipeDialogViewModel,
) {
    val nameTextState by viewModel.nameTextState
    val componentsTextState by viewModel.componentsTextState
    val instructionsTextState by viewModel.instructionsTextState
    val sugarTextState by viewModel.sugarTextState
    val fatTextState by viewModel.fatTextState
    val proteinTextState by viewModel.proteinTextState
    val fiberTextState by viewModel.fiberTextState
    val carbohydratesTextState by viewModel.carbohydratesTextState
    val caloriesTextState by viewModel.caloriesTextState

    val loadingState by viewModel.loadingState



    Dialog(
        onDismissRequest = {
            onDismiss()
        }
    ) {
        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        modifier = Modifier.padding(bottom = 16.dp),
                        text = "Edit recipe",
                        style = MaterialTheme.typography.h6,
                    )
                    Text("Name")
                    OutlinedTextField(
                        modifier = Modifier.padding(bottom = 16.dp),
                        value = nameTextState,
                        onValueChange = {
                            viewModel.updateNameTextState(it)
                        },
                    )
                    Text("Components")
                    OutlinedTextField(
                        modifier = Modifier.padding(bottom = 16.dp),
                        value = componentsTextState,
                        onValueChange = {
                            viewModel.updateComponentsTextState(it)
                        },
                    )
                    Text("Instructions")
                    OutlinedTextField(
                        modifier = Modifier.padding(bottom = 16.dp),
                        value = instructionsTextState,
                        onValueChange = {
                            viewModel.updateInstructionsTextState(it)
                        },
                    )
                    Text("Nutrition")
                    Column(
                        modifier = Modifier.padding(bottom = 16.dp),
                    ) {
                        OutlinedTextField(
                            modifier = Modifier.padding(bottom = 4.dp),
                            value = sugarTextState,
                            onValueChange = {
                                viewModel.updateSugarTextState(it)
                            },
                            label = { Text("Sugar") },
                            maxLines = 1,
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number
                            ),
                        )
                        OutlinedTextField(
                            modifier = Modifier.padding(bottom = 4.dp),
                            value = fatTextState,
                            onValueChange = {
                                viewModel.updateFatTextState(it)
                            },
                            label = { Text("Fat") },
                            maxLines = 1,
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number
                            ),
                        )
                        OutlinedTextField(
                            modifier = Modifier.padding(bottom = 4.dp),
                            value = proteinTextState,
                            onValueChange = {
                                viewModel.updateProteinTextState(it)
                            },
                            label = { Text("Protein") },
                            maxLines = 1,
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number
                            ),
                        )
                        OutlinedTextField(
                            modifier = Modifier.padding(bottom = 4.dp),
                            value = fiberTextState,
                            onValueChange = {
                                viewModel.updateFiberTextState(it)
                            },
                            label = { Text("Fiber") },
                            maxLines = 1,
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number
                            ),
                        )
                        OutlinedTextField(
                            modifier = Modifier.padding(bottom = 4.dp),
                            value = carbohydratesTextState,
                            onValueChange = {
                                viewModel.updateCarbohydratesTextState(it)
                            },
                            label = { Text("Carbohydrates") },
                            maxLines = 1,
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number
                            ),
                        )
                        OutlinedTextField(
                            value = caloriesTextState,
                            onValueChange = {
                                viewModel.updateCaloriesTextState(it)
                            },
                            label = { Text("Calories") },
                            maxLines = 1,
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number
                            ),
                        )
                    }
                }
                Row(
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            viewModel.editRecipe (
                                onSuccess = onEdit,
                            )
                        }
                    ) {
                        Text("Edit")
                    }
                }
            }

            if (viewModel.errorState.value != ErrorState.Empty) {
                val error = viewModel.errorState.value as ErrorState.Error
                ErrorDialog(
                    message = error.message,
                    onDismiss = {
                        viewModel.updateErrorState(ErrorState.Empty)
                    },
                    onSubmit = {
                        viewModel.updateErrorState(ErrorState.Empty)
                    },
                )
            }

            AnimatedVisibility(
                visible = loadingState,
                enter = fadeIn(),
                exit = fadeOut(),
            ) {
                Loading()
            }
        }
    }
}