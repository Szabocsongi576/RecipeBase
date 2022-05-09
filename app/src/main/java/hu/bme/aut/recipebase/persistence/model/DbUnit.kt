package hu.bme.aut.recipebase.persistence.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DbUnit(
    @PrimaryKey(autoGenerate = true) var id: Long,
    var measurementId: String?,
    var system: String?,
    var name: String?,
    var displayPlural: String?,
    var displaySingular: String?,
    var abbreviation: String?,
)

