package hu.bme.aut.recipebase.persistence.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.math.BigDecimal

@Entity
data class DbTag(
    @PrimaryKey var id: BigDecimal,
    var recipeId: String,
    var name: String?,
    var displayName: String?,
    var type: String?,
)