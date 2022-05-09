package hu.bme.aut.recipebase.persistence.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.math.BigDecimal

@Entity
data class DbIngredient(
    @PrimaryKey var id: BigDecimal,
    var displaySingular: String?,
    var updatedAt: BigDecimal?,
    var name: String?,
    var createdAt: BigDecimal?,
    var displayPlural: String?,
)