package hu.bme.aut.recipebase.persistence.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import io.swagger.annotations.ApiModelProperty
import java.math.BigDecimal
import java.time.OffsetDateTime

@Entity
data class DbMeasurement(
    @PrimaryKey var id: BigDecimal,
    var componentId: String,
    var quantity: String?,
)