package hu.bme.aut.recipebase.persistence.model.switch

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Relation
import hu.bme.aut.recipebase.persistence.model.*

@Entity
data class DbMeasurementWithUnit(
    @Embedded
    val measurement: DbMeasurement,
    @Relation(
        parentColumn = "id",
        entityColumn = "measurementId"
    )
    val unit: DbUnit
)

