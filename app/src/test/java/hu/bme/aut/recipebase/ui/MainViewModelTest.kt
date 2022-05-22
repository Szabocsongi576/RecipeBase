package hu.bme.aut.recipebase.ui

import android.app.Application
import com.google.common.truth.Truth
import hu.bme.aut.recipebase.createNetworkRecipe
import hu.bme.aut.recipebase.network.model.RecipeList
import hu.bme.aut.recipebase.ui.activity.main.MainRepository
import hu.bme.aut.recipebase.ui.activity.main.MainViewModel
import hu.bme.aut.recipebase.ui.state.UiState
import io.kotest.core.spec.style.StringSpec
import io.kotest.core.test.TestCase
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Test
import java.lang.Exception

@ExperimentalCoroutinesApi
class MainViewModelTest : StringSpec({
    val application: Application = mockk()
    val repository: MainRepository = mockk()

    val recipeList = RecipeList()
        .addResultsItem(createNetworkRecipe(id = 1, name = "Test 1"))
        .addResultsItem(createNetworkRecipe(id = 2, name = "Test 2"))
        .addResultsItem(createNetworkRecipe(id = 3, name = "Test 3"))

    "load should get recipes from repository" {
        coEvery { repository.fetchRecipes(
            from = 0,
            size = 3,
            query = null,
        ) } returns recipeList

        val viewModel = MainViewModel(application, repository)

        verify { viewModel.init() }
        verify { viewModel.onRecipesFetched() }
        assert(viewModel.uiState.value is UiState.Loaded)
    }

    "if load failed, UiState should change to Error" {
        coEvery { repository.fetchRecipes(
            from = 0,
            size = 3,
            query = null,
        ) } throws Exception("e")

        val viewModel = MainViewModel(application, repository)

        assert(viewModel.uiState.value is UiState.Error)
    }

    "search recipes should get recipes from repository" {
        coEvery { repository.fetchRecipes(
            from = 0,
            size = 3,
            query = "Test",
        ) } returns recipeList

        val viewModel = MainViewModel(application, repository)
        viewModel.updateSearchTextState("Test")
        viewModel.searchRecipes {}

        coVerify {
            repository.fetchRecipes(
                query = "Test",
                from = 0,
                size = MainViewModel.FETCH_SIZE,
            )
        }
        verify { viewModel.onRecipesFetched() }
        assert(!viewModel.centerLoadingState.value)
    }

    "delete recipe should refresh the list after deletion" {
        coEvery { repository.deleteRecipe(id = 1) } returns Unit

        val viewModel = MainViewModel(application, repository)
        viewModel.deleteRecipe(createNetworkRecipe(id = 1, name = "Test name")) {}

        coVerify { repository.deleteRecipe(id = 1) }
        verify { viewModel.onRecipeDeleted() }
        assert(!viewModel.centerLoadingState.value)
    }

    "add to favorite should refresh the list of favorites" {
        coEvery { repository.writeFavorite(createNetworkRecipe(id = 1, name = "Test name")) } returns Unit

        val viewModel = MainViewModel(application, repository)
        viewModel.addToFavorite(createNetworkRecipe(id = 1, name = "Test name")) {}

        coVerify { repository.writeFavorite(createNetworkRecipe(id = 1, name = "Test name")) }
        Truth.assertThat(viewModel.favoriteListState.value).containsExactly(createNetworkRecipe(id = 1, name = "Test name"))
        assert(!viewModel.centerLoadingState.value)
    }

    "delete from favorite should refresh the list of favorites" {
        coEvery { repository.deleteRecipe(id = 1) } returns Unit

        val viewModel = MainViewModel(application, repository)
        viewModel.deleteFromFavorite(createNetworkRecipe(id = 1, name = "Test name")) {}

        coVerify { repository.deleteRecipe(id = 1) }
        assert(!viewModel.centerLoadingState.value)
    }
}) {
    override fun beforeTest(testCase: TestCase) {
        super.beforeTest(testCase)
        clearAllMocks()
    }
}
