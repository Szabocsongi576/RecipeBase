package hu.bme.aut.recipebase.ui.activity.recipe_details

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.unit.dp
import com.google.android.material.color.MaterialColors
import hu.bme.aut.recipebase.network.model.Recipe
import hu.bme.aut.recipebase.ui.components.RecipeImage
import hu.bme.aut.recipebase.ui.components.app_bar.RecipeDetailsAppBar

@Composable
fun RecipeDetailsScreen(viewModel: RecipeDetailsViewModel) {
    val recipeState by viewModel.recipeState

    val context = LocalContext.current

    Log.i("TAG", recipeState!!.id.toString())

    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            RecipeDetailsAppBar(
                onBackClicked = {
                    context.onBackPressed()
                }
            )
        },
    ) {
        Column(
            modifier = Modifier.verticalScroll(scrollState),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp),
            ) {
                RecipeImage(recipe = recipeState!!)
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            brush = Brush.linearGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    Color.White,
                                ),
                                start = Offset(0.0f, 200.dp.value),
                                end = Offset(0.0f, Float.POSITIVE_INFINITY),
                                tileMode = TileMode.Clamp,
                            )
                        )
                )
            }

            Box(
                modifier = Modifier.padding(8.dp)
            ) {
                Text(
                    text = recipeState!!.name!!,
                    style = MaterialTheme.typography.h4,
                )
            }

            Box(
                modifier = Modifier.padding(8.dp)
            ) {
                Card(
                    backgroundColor = Color.LightGray,
                    shape = RoundedCornerShape(corner = CornerSize(16.dp))
                ) {
                    Column(
                        modifier = Modifier.padding(8.dp),
                    ) {
                        Components(recipeState!!)
                        Instructions(recipeState!!)
                        Nutrition(recipeState!!)
                    }
                }
            }
        }
    }
}

@Composable
fun Components(recipeState: Recipe) {
    val bullet = "\u2022"

    Text(
        text = "Components:",
        style = MaterialTheme.typography.h6,
    )

    recipeState.getSections()?.forEach { section ->
        Column {
            if (section.name != null) {
                Spacer(modifier = Modifier.height(15.dp))
                Text(
                    text = section.name!!,
                    style = MaterialTheme.typography.subtitle1,
                )
            }
            Spacer(modifier = Modifier.height(15.dp))
            section.getComponents()?.forEach { component ->
                Text(
                    buildAnnotatedString {
                        append(bullet)
                        append("\t\t")
                        append("${component.rawText}")
                    },
                    style = MaterialTheme.typography.body1,
                )
            }

        }
    }
}

@Composable
fun Instructions(recipeState: Recipe) {
    val bullet = "\u2022"

    if (recipeState.getInstructions()?.isNotEmpty() == true) {
        Column {
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "Instructions:",
                style = MaterialTheme.typography.h6,
            )
            Spacer(modifier = Modifier.height(15.dp))
            recipeState.getInstructions()?.forEach { instruction ->
                Text(
                    buildAnnotatedString {
                        append(bullet)
                        append("\t\t")
                        append("${instruction.displayText}")
                    },
                    style = MaterialTheme.typography.body1,
                )
            }
        }
    }
}

@Composable
fun Nutrition(recipeState: Recipe) {
    val bullet = "\u2022"

    val isDisplay: Boolean =
        (recipeState.nutrition?.sugar != null) ||
                (recipeState.nutrition?.fiber != null) ||
                (recipeState.nutrition?.calories != null) ||
                (recipeState.nutrition?.carbohydrates != null) ||
                (recipeState.nutrition?.fat != null) ||
                (recipeState.nutrition?.protein != null)

    if (isDisplay) {
        Column {
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "Nutrition:",
                style = MaterialTheme.typography.h6,
            )
            Spacer(modifier = Modifier.height(15.dp))
            if (recipeState.nutrition?.sugar != null) {
                Text(
                    buildAnnotatedString {
                        append(bullet)
                        append("\t\t")
                        append("Sugar: ${recipeState.nutrition!!.sugar!!}")
                    },
                    style = MaterialTheme.typography.body1,
                )
            }
            if (recipeState.nutrition?.fiber != null) {
                Text(
                    buildAnnotatedString {
                        append(bullet)
                        append("\t\t")
                        append("Fiber: ${recipeState.nutrition!!.fiber!!}")
                    },
                    style = MaterialTheme.typography.body1,
                )
            }
            if (recipeState.nutrition?.calories != null) {
                Text(
                    buildAnnotatedString {
                        append(bullet)
                        append("\t\t")
                        append("Calories: ${recipeState.nutrition!!.calories!!}")
                    },
                    style = MaterialTheme.typography.body1,
                )
            }
            if (recipeState.nutrition?.carbohydrates != null) {
                Text(
                    buildAnnotatedString {
                        append(bullet)
                        append("\t\t")
                        append("Carbohydrates: ${recipeState.nutrition!!.carbohydrates!!}")
                    },
                    style = MaterialTheme.typography.body1,
                )
            }
            if (recipeState.nutrition?.fat != null) {
                Text(
                    buildAnnotatedString {
                        append(bullet)
                        append("\t\t")
                        append("Fat: ${recipeState.nutrition!!.fat!!}")
                    },
                    style = MaterialTheme.typography.body1,
                )
            }
            if (recipeState.nutrition?.protein != null) {
                Text(
                    buildAnnotatedString {
                        append(bullet)
                        append("\t\t")
                        append("Protein: ${recipeState.nutrition!!.protein!!}")
                    },
                    style = MaterialTheme.typography.body1,
                )
            }
        }
    }
}