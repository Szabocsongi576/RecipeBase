package hu.bme.aut.recipebase.persistence

import androidx.room.*
import hu.bme.aut.recipebase.persistence.model.DbRecipe

@Dao
interface RecipeDao {
    @Query("SELECT * FROM recipe")
    suspend fun getAll(): List<DbRecipe>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(vararg recipe: DbRecipe)

    @Delete
    suspend fun delete(vararg recipe: DbRecipe)
}