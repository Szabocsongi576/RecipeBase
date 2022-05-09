package hu.bme.aut.recipebase.persistence.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DbCredit(
    @PrimaryKey(autoGenerate = true) var id: Long,
    var recipeId: String,
    var name: String?,
    var type: String?,
)