package hu.bme.aut.recipebase.persistence.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.math.BigDecimal

@Entity
data class DbInstruction(
    @PrimaryKey var id: BigDecimal,
    var recipeId: String,
    var componentId: String,
    var startTime: BigDecimal?,
    var appliance: String?,
    var endTime: BigDecimal?,
    var temperature: String?,
    var position: BigDecimal?,
    var displayText: String?,
)