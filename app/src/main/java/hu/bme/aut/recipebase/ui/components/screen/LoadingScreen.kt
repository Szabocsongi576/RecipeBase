package hu.bme.aut.recipebase.ui.components.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import hu.bme.aut.recipebase.ui.components.app_bar.DefaultAppBar

@Composable
fun LoadingScreen(
    topBar: @Composable () -> Unit = {
        DefaultAppBar()
    },
) {
    Scaffold(
        topBar = topBar
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator()
        }
    }
}

@Composable
@Preview
fun PreviewLoadingScreen() {
    LoadingScreen()
}