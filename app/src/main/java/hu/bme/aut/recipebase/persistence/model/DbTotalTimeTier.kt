package hu.bme.aut.recipebase.persistence.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DbTotalTimeTier(
    @PrimaryKey(autoGenerate = true) var id: Long,
    var recipeId: String,
    var tier: String?,
    var displayTier: String?,
)