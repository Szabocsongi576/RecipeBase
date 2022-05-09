package hu.bme.aut.recipebase.persistence.model.switch

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Relation
import hu.bme.aut.recipebase.persistence.model.DbComponent
import hu.bme.aut.recipebase.persistence.model.DbSection

@Entity
data class DbSectionWithComponents(
    @Embedded
    val section: DbSection,
    @Relation(
        parentColumn = "id",
        entityColumn = "sectionId"
    )
    val components: List<DbComponent>
)

