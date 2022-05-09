package hu.bme.aut.recipebase.persistence.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DbTopics(
    @PrimaryKey(autoGenerate = true) var id: Long,
    var recipeId: String,
    var slug: String?,
    var name: String?,
)