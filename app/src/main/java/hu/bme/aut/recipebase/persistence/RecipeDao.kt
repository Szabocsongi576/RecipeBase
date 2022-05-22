package hu.bme.aut.recipebase.persistence

import androidx.room.*
import hu.bme.aut.recipebase.persistence.model.DbRecipe

@Dao
interface RecipeDao {
    @Query("SELECT * FROM recipe")
    fun getAll(): List<DbRecipe>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdate(vararg recipe: DbRecipe)

    @Delete
    fun delete(vararg recipe: DbRecipe)
}