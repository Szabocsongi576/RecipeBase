package hu.bme.aut.recipebase.ui.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.*
import hu.bme.aut.recipebase.network.model.Recipe
import hu.bme.aut.recipebase.ui.activity.main.MainViewModel
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import java.math.BigDecimal

@Composable
fun RecipeList(
    recipes: List<Recipe>,
    indexOfFetchTriggerState: State<Long>,
    enableFetchTriggerState: State<Boolean>,
    onFetch: () -> Unit,
    onItemClick: (id: BigDecimal) -> Unit,
    onItemDeleteClick: (id: BigDecimal) -> Unit,
    onItemEditClicked: (recipe: Recipe) -> Unit,
    listState: LazyListState,
) {
    val fetchCount: MutableState<Long> = remember { mutableStateOf(0) }

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
                onDeleteClicked = { onItemDeleteClick(recipe.id!!) },
                onEditClicked = { onItemEditClicked(recipe) }
            )
        }
    }

    LaunchedEffect(listState) {
        snapshotFlow { listState.firstVisibleItemIndex }
            .map { index -> index > indexOfFetchTriggerState.value && enableFetchTriggerState.value }
            .distinctUntilChanged()
            .filter { it }
            .collect {
                onFetch()
                fetchCount.value++
            }
    }
}