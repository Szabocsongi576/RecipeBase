package hu.bme.aut.recipebase.ui.components.app_bar

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.West
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun RecipeDetailsAppBar(onDeleteClicked: () -> Unit = {}, onBackClicked: () -> Unit = {}) {
    TopAppBar(
        title = {
            Text(
                text = "Recipe details",
                color = Color.Black,
            )
        },
        actions = {
            IconButton(
                onClick = { onDeleteClicked() }
            ) {
                Icon(
                    imageVector = Icons.Filled.Delete,
                    contentDescription = "Delete Icon",
                    tint = Color.Black
                )
            }
        },
        navigationIcon = {
            IconButton(
                onClick = { onBackClicked() }
            ) {
                Icon(
                    imageVector = Icons.Filled.West,
                    contentDescription = "Delete Icon",
                    tint = Color.Black
                )
            }
        }
    )
}



@Composable
@Preview
fun PreviewRecipeDetailsAppBar() {
    RecipeDetailsAppBar()
}