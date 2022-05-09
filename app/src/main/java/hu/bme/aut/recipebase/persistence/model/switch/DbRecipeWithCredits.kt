package hu.bme.aut.recipebase.persistence.model.switch

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Relation
import hu.bme.aut.recipebase.persistence.model.DbCredit
import hu.bme.aut.recipebase.persistence.model.DbInstruction
import hu.bme.aut.recipebase.persistence.model.DbRecipe
import hu.bme.aut.recipebase.persistence.model.DbSection

@Entity
data class DbRecipeWithCredits(
    @Embedded
    val recipe: DbRecipe,
    @Relation(
        parentColumn = "id",
        entityColumn = "recipeId"
    )
    val credits: List<DbCredit>
)

