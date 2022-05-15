package hu.bme.aut.recipebase.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import hu.bme.aut.recipebase.persistence.converter.Converters
import hu.bme.aut.recipebase.persistence.model.*

@Database(
    entities = [
        DbComponent::class,
        DbCredit::class,
        DbIngredient::class,
        DbInstruction::class,
        DbMeasurement::class,
        DbNutrition::class,
        DbRecipe::class,
        DbSection::class,
        DbShow::class,
        DbTag::class,
        DbTopics::class,
        DbTotalTimeTier::class,
        DbUnit::class,
        DbUserRating::class,
    ], version = 1, exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun recipeDao(): RecipeDao
}