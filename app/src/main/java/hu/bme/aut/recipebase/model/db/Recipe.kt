package hu.bme.aut.recipebase.model.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Recipe(
    @PrimaryKey(autoGenerate = true) var recipeId: Long?,
    @ColumnInfo(name = "name") var name: String
)
