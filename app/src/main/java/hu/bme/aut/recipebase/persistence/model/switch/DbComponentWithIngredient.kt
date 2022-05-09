package hu.bme.aut.recipebase.persistence.model.switch

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Relation
import hu.bme.aut.recipebase.persistence.model.DbComponent
import hu.bme.aut.recipebase.persistence.model.DbIngredient

@Entity
data class DbComponentWithIngredient(
    @Embedded
    val component: DbComponent,
    @Relation(
        parentColumn = "id",
        entityColumn = "componentId"
    )
    val ingredient: DbIngredient
)

