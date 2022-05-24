package hu.bme.aut.recipebase

import com.google.gson.Gson
import hu.bme.aut.recipebase.network.model.*
import hu.bme.aut.recipebase.persistence.model.DbRecipe
import org.json.JSONObject

fun createNetworkRecipe(
    id: Long = 1,
    name: String = "Test name",
): Recipe {
    val recipe = Recipe()

    recipe.id = id
    recipe.name = name
    recipe.thumbnailUrl = "https://via.placeholder.com/150"

    val section = Section()
    val components: MutableList<Component> = mutableListOf()
    for (i in 1..3) {
        val component = Component()
        component.rawText = "Component $i"
        components.add(component)
    }

    section.setComponents(components)
    recipe.setSections(mutableListOf(section))

    val instructions: MutableList<Instruction> = mutableListOf()
    for (i in 1..3) {
        val instruction = Instruction()
        instruction.displayText = "Instruction $i"
        instructions.add(instruction)
    }

    recipe.setInstructions(instructions)

    val nutrition = Nutrition()
    nutrition.sugar = 5
    nutrition.fat = 2
    nutrition.protein = 4
    nutrition.fiber = 3
    nutrition.carbohydrates = 1
    nutrition.calories = 500

    recipe.nutrition = nutrition

    return recipe
}

fun createDbRecipe(
    id: Long = 1,
    name: String = "Test name",
): DbRecipe {
    return DbRecipe(
        id = id,
        recipe = """{"instructions":[{"display_text":"Instruction 1"},{"display_text":"Instruction 2"},{"display_text":"Instruction 3"}],"sections":[{"components":[{"raw_text":"Component 1"},{"raw_text":"Component 2"},{"raw_text":"Component 3"}]}],"thumbnail_url":"https://via.placeholder.com/150","id":$id,"name":"$name","nutrition":{"protein":4,"fat":2,"calories":500,"sugar":5,"carbohydrates":1,"fiber":3}}""",
    )
}

