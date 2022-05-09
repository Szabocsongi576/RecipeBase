package hu.bme.aut.recipebase.persistence.model.switch

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Relation
import hu.bme.aut.recipebase.persistence.model.DbNutrition
import hu.bme.aut.recipebase.persistence.model.DbRecipe
import hu.bme.aut.recipebase.persistence.model.DbTotalTimeTier
import hu.bme.aut.recipebase.persistence.model.DbUserRating

@Entity
data class DbRecipeWithNutrition(
    @Embedded
    val recipe: DbRecipe,
    @Relation(
        parentColumn = "id",
        entityColumn = "recipeId"
    )
    val nutrition: DbNutrition
)

