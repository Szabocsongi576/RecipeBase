package hu.bme.aut.recipebase.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ErrorDialog(
    onDismiss: () -> Unit,
    onSubmit: () -> Unit,
    message: String,
) {
    AlertDialog(
        onDismissRequest = {
            onDismiss()
        },
        title = {
            Text(
                text = "Error",
                style = MaterialTheme.typography.h6,
            )
        },
        text = {
            Text(
                text = message,
            )
        },
        buttons = {
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                onClick = {
                    onSubmit()
                }
            ) {
                Text("Ok")
            }
        }
    )
}