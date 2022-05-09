package hu.bme.aut.recipebase.persistence.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.math.BigDecimal
import java.util.*

@Entity
data class DbNutrition(
    @PrimaryKey(autoGenerate = true) var id: Long,
    var recipeId: String,
    var updatedAt: Date?,
    var protein: BigDecimal?,
    var fat: BigDecimal?,
    var calories: BigDecimal?,
    var sugar: BigDecimal?,
    var carbohydrates: BigDecimal?,
    var fiber: BigDecimal?,
)