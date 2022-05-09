package hu.bme.aut.recipebase.persistence.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.math.BigDecimal

@Entity
data class DbComponent(
    @PrimaryKey var id: BigDecimal,
    var sectionId: String,
    var extraComment: String?,
    var position: BigDecimal?,
    var rawText: String?,
)

