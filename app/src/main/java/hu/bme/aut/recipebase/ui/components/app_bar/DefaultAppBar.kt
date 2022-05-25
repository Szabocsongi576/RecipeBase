package hu.bme.aut.recipebase.ui.components.app_bar

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun DefaultAppBar(
    onSearchClicked: (() -> Unit)? = null,
    onFavoritesClicked: (() -> Unit)? = null,
    onBackClicked: (() -> Unit)? = null,
    title: String? = null,
) {
    val navigationIcon: @Composable (() -> Unit)? = if (onBackClicked != null) {
        {
            IconButton(
                onClick = { onBackClicked() }
            ) {
                Icon(
                    imageVector = Icons.Filled.West,
                    contentDescription = "Back Icon",
                    tint = Color.Black
                )
            }
        }
    } else {
        null
    }

    TopAppBar(
        title = {
            if (title != null) {
                Text(
                    text = title
                )
            }
        },
        navigationIcon = navigationIcon,
        actions = {
            IconButton(
                onClick = {
                    throw RuntimeException("Test Crash") // Force a crash
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.Send,
                    contentDescription = "Trigger crash",
                    tint = Color.Black
                )
            }
            if (onFavoritesClicked != null) {
                IconButton(
                    onClick = { onFavoritesClicked() }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Favorite,
                        contentDescription = "Favorite Icon",
                        tint = Color.Black
                    )
                }
            }
            if (onSearchClicked != null) {
                IconButton(
                    onClick = { onSearchClicked() }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = "Search Icon",
                        tint = Color.Black
                    )
                }
            }
        }
    )
}


@Composable
@Preview
fun DefaultAppBarPreview() {
    DefaultAppBar(onSearchClicked = {})
}