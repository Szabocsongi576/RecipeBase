package hu.bme.aut.recipebase.persistence.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.math.BigDecimal

@Entity
data class DbUserRating(
    @PrimaryKey(autoGenerate = true) var id: Long,
    var recipeId: String,
    var countPositive: BigDecimal?,
    var score: String?,
    var countNegative: BigDecimal?,
)