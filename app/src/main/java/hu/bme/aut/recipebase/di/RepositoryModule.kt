package hu.bme.aut.recipebase.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hu.bme.aut.recipebase.network.api.RecipesApi
import hu.bme.aut.recipebase.persistence.RecipeDao
import hu.bme.aut.recipebase.ui.activity.main.MainRepository
import hu.bme.aut.recipebase.ui.activity.recipe_details.RecipeDetailsRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMainRepository(api: RecipesApi, dao: RecipeDao) = MainRepository(
        recipesApi = api,
        recipeDao = dao,
    )

    @Singleton
    @Provides
    fun provideReceiptDetailsRepository(api: RecipesApi, dao: RecipeDao) = RecipeDetailsRepository(
        recipesApi = api,
        recipeDao = dao,
    )
}