package hu.bme.aut.recipebase.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import hu.bme.aut.recipebase.ui.components.app_bar.DefaultAppBar

@Composable
fun ErrorScreen(message: String) {
    Scaffold(
        topBar = {
            DefaultAppBar(
                onSearchClicked = {},
            )
        },
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
            ) {
                Text(text = "Error", style = MaterialTheme.typography.h4, color = Color.Black)
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = message,
                    style = MaterialTheme.typography.subtitle1,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}

@Composable
@Preview
fun PreviewErrorScreen() {
    ErrorScreen("Error message")
}