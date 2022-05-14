package hu.bme.aut.recipebase.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
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
fun BottomLoading() {
    Column(
        modifier = Modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.Bottom,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ) {
            Box(modifier = Modifier
                .height(60.dp)
                .width(60.dp)
                .padding(16.dp)
            ) {
                CircularProgressIndicator()
            }
        }
    }
}

@Composable
@Preview
fun PreviewBottomLoading() {
    BottomLoading()
}