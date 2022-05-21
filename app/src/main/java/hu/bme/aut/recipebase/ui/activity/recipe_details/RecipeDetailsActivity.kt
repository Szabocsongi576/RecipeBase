package hu.bme.aut.recipebase.ui.activity.recipe_details

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import hu.bme.aut.recipebase.network.model.Component
import hu.bme.aut.recipebase.network.model.Instruction
import hu.bme.aut.recipebase.network.model.Recipe
import hu.bme.aut.recipebase.network.model.Nutrition
import hu.bme.aut.recipebase.network.model.Section
import hu.bme.aut.recipebase.ui.dialog.edit_recipe.EditRecipeDialogViewModel
import hu.bme.aut.recipebase.ui.theme.RecipeBaseTheme
import java.math.BigDecimal

@AndroidEntryPoint
class RecipeDetailsActivity : ComponentActivity() {
    private val recipeDetailsViewModel: RecipeDetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init()

        setContent {
            RecipeBaseTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    RecipeDetailsScreen(
                        recipeDetailsViewModel = recipeDetailsViewModel,
                        onBackClicked = {
                            finish()
                        },
                    )
                }
            }
        }
    }

    private fun init() {
        val recipeId = intent.getLongExtra("recipe_id", -1)

        val nameExtra = intent.getStringExtra("name")
        val imageExtra = intent.getStringExtra("image")

        val componentsExtra = intent.getStringArrayListExtra("components")
        val instructionsExtra = intent.getStringArrayListExtra("instructions")

        val sugarExtra = intent.getLongExtra("sugar", -1)
        val fatExtra = intent.getLongExtra("fat", -1)
        val proteinExtra = intent.getLongExtra("protein", -1)
        val fiberExtra = intent.getLongExtra("fiber", -1)
        val carbohydratesExtra = intent.getLongExtra("carbohydrates", -1)
        val caloriesExtra = intent.getLongExtra("calories", -1)

        val recipe = Recipe()
        recipe.id = BigDecimal(recipeId)
        recipe.name = nameExtra
        recipe.thumbnailUrl = imageExtra

        val section = Section()
        val components: MutableList<Component> = mutableListOf()
        componentsExtra!!.forEach {
            val component = Component()
            component.rawText = it
            components.add(component)
        }

        section.setComponents(components)
        recipe.setSections(mutableListOf(section))

        val instructions: MutableList<Instruction> = mutableListOf()
        instructionsExtra!!.forEach {
            val instruction = Instruction()
            instruction.displayText = it
            instructions.add(instruction)
        }

        recipe.setInstructions(instructions)

        val nutrition = Nutrition()
        if(sugarExtra != (-1).toLong()) {
            nutrition.sugar = BigDecimal(sugarExtra)
        }
        if(fatExtra != (-1).toLong()) {
            nutrition.fat = BigDecimal(fatExtra)
        }
        if(proteinExtra != (-1).toLong()) {
            nutrition.protein = BigDecimal(proteinExtra)
        }
        if(fiberExtra != (-1).toLong()) {
            nutrition.fiber = BigDecimal(fiberExtra)
        }
        if(carbohydratesExtra != (-1).toLong()) {
            nutrition.carbohydrates = BigDecimal(carbohydratesExtra)
        }
        if(caloriesExtra != (-1).toLong()) {
            nutrition.calories = BigDecimal(caloriesExtra)
        }

        recipe.nutrition = nutrition

        recipeDetailsViewModel.setRecipe(recipe)
    }
}