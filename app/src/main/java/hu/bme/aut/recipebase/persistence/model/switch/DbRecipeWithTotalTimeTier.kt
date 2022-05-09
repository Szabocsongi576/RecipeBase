package hu.bme.aut.recipebase.persistence.model.switch

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Relation
import hu.bme.aut.recipebase.persistence.model.DbRecipe
import hu.bme.aut.recipebase.persistence.model.DbTotalTimeTier
import hu.bme.aut.recipebase.persistence.model.DbUserRating

@Entity
data class DbRecipeWithTotalTimeTier(
    @Embedded
    val recipe: DbRecipe,
    @Relation(
        parentColumn = "id",
        entityColumn = "recipeId"
    )
    val totalTimeTier: DbTotalTimeTier
)

