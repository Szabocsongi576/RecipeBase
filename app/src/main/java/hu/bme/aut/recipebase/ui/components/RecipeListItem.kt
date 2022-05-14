package hu.bme.aut.recipebase.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import hu.bme.aut.recipebase.network.model.Recipe

@Composable
fun RecipeListItem(recipe: Recipe = Recipe(), onClick: () -> Unit = {}) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(8.dp)
            .clickable { onClick() },
        elevation = 10.dp,
        backgroundColor = Color.White,
        shape = RoundedCornerShape(corner = CornerSize(16.dp))
    ) {
        RecipeImage(recipe = recipe)
        LinearGradient(startY = 125.dp.value)
        Row(
            modifier = Modifier
                .padding(16.dp),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Start,
        ) {
            Text(text = recipe.name ?: "Name", style = typography.h6, color = Color.White)
        }
    }
}

@Composable
fun LinearGradient(startY: Float) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color.Transparent,
                        Color.Black.copy(
                            alpha = 0.75f,
                        ),
                    ),
                    start = Offset(0.0f, startY),
                    end = Offset(0.0f, Float.POSITIVE_INFINITY),
                    tileMode = TileMode.Clamp,
                )
            )
    )
}

@Composable
@Preview
fun PreviewRecipeListItem() {
    RecipeListItem()
}