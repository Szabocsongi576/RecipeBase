package hu.bme.aut.recipebase.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import hu.bme.aut.recipebase.network.model.Recipe

@Composable
fun RecipeImage(recipe: Recipe = Recipe()) {
    Box(modifier = Modifier.fillMaxWidth()) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(recipe.thumbnailUrl ?: "https://via.placeholder.com/150")
                .crossfade(true)
                .build(),
            contentDescription = "Recipe image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
@Preview
fun PreviewRecipeImage() {
    RecipeImage()
}