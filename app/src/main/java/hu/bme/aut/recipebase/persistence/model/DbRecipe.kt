package hu.bme.aut.recipebase.persistence.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.Gson
import hu.bme.aut.recipebase.network.model.Recipe


@Entity(tableName = "recipe")
data class DbRecipe(
    @PrimaryKey(autoGenerate = false) var id: Long,
    var recipe: String,
) {
    companion object {
        fun fromDomainObject(recipe: Recipe) = DbRecipe(
            id = recipe.id ?: 0,
            recipe = Gson().toJson(recipe),
        )
    }

    fun toDomainObject(): Recipe {
        return Gson().fromJson(this.recipe, Recipe::class.java)
    }
}