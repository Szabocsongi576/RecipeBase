package hu.bme.aut.recipebase.persistence.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.math.BigDecimal

@Entity
data class DbSection(
    @PrimaryKey(autoGenerate = true) var id: Long,
    var recipeId: String,
    var name: String?,
    var position: BigDecimal?,
)