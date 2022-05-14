package hu.bme.aut.recipebase.ui.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.snapshotFlow
import hu.bme.aut.recipebase.network.model.Recipe
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import java.math.BigDecimal

@Composable
fun RecipeList(
    recipes: List<Recipe>,
    indexOfFetchTriggerState: State<Long>,
    onFetch: () -> Unit,
    onItemClick: (id: BigDecimal) -> Unit,
) {
    val listState = rememberLazyListState()

    LazyColumn(state = listState) {
        items(
            items = recipes,
            key = { recipe ->
                recipe.id!!
            }
        ) { recipe ->
            RecipeListItem(
                recipe = recipe,
                onClick = {
                    onItemClick(recipe.id!!)
                },
            )
        }
    }

    LaunchedEffect(listState) {
        snapshotFlow { listState.firstVisibleItemIndex }
            .map { index -> index > indexOfFetchTriggerState.value }
            .distinctUntilChanged()
            .filter { it }
            .collect {
                onFetch()
            }
    }
}