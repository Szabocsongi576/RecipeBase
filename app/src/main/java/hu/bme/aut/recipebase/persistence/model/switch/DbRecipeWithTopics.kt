package hu.bme.aut.recipebase.persistence.model.switch

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Relation
import hu.bme.aut.recipebase.persistence.model.DbRecipe
import hu.bme.aut.recipebase.persistence.model.DbTopics

@Entity
data class DbRecipeWithTopics(
    @Embedded
    val recipe: DbRecipe,
    @Relation(
        parentColumn = "id",
        entityColumn = "recipeId"
    )
    val topics: List<DbTopics>
)

