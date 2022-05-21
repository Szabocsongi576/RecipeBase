package hu.bme.aut.recipebase.ui.components.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun NoDataScreen() {
    Scaffold {
        TopAppBar(
            title = {
                Text(
                    text = "Recipes"
                )
            },
        )
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "No data available",
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@Composable
@Preview
fun PreviewNoDataScreen() {
    NoDataScreen()
}