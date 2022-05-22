package hu.bme.aut.recipebase

import hu.bme.aut.recipebase.network.model.*
import hu.bme.aut.recipebase.persistence.model.DbRecipe

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
        recipe = """{
    "approved_at": null,
    "aspect_ratio": null,
    "canonical_id": null,
    "compilations": null,
    "cook_time_minutes": null,
    "country": null,
    "created_at": null,
    "credits": null,
    "description": null,
    "draft_status": null,
    "facebook_posts": null,
    "id": $id,
    "instructions": [
    {
        "display_text": "Instruction 1",
        "end_time": null,
        "id": null,
        "position": null,
        "start_time": null
    },
    {
        "display_text": "Instruction 2",
        "end_time": null,
        "id": null,
        "position": null,
        "start_time": null
    },
    {
        "display_text": "Instruction 3",
        "end_time": null,
        "id": null,
        "position": null,
        "start_time": null
    }
    ],
    "is_one_top": null,
    "is_shoppable": null,
    "tips_and_ratings_enabled": null,
    "keywords": null,
    "language": null,
    "name": $name,
    "num_servings": null,
    "nutrition": {
        "calories": 500,
        "carbohydrates": 1,
        "fat": 2,
        "fiber": 3,
        "protein": 4,
        "sugar": 5,
        "updated_at": null
    },
    "nutrition_visibility": null,
    "prep_time_minutes": null,
    "promotion": null,
    "renditions": null,
    "sections": [
    {
        "components": [
        {
            "extra_comment": null,
            "id": null,
            "ingredient": null,
            "measurements": null,
            "position": null,
            "raw_text": "Component 1"
        },
        {
            "extra_comment": null,
            "id": null,
            "ingredient": null,
            "measurements": null,
            "position": null,
            "raw_text": "Component 2"
        },
        {
            "extra_comment": null,
            "id": null,
            "ingredient": null,
            "measurements": null,
            "position": null,
            "raw_text": "Component 3"
        }
        ],
        "position": null
    }
    ],
    "seo_title": null,
    "servings_noun_plural": null,
    "servings_noun_singular": null,
    "show": null,
    "show_id": null,
    "slug": null,
    "tags": null,
    "thumbnail_alt_text": null,
    "thumbnail_url": "https://via.placeholder.com/150",
    "topics": null,
    "total_time_minutes": null,
    "total_time_tier": null,
    "updated_at": null,
    "user_ratings": null,
    "yields": null
}""",
    )
}