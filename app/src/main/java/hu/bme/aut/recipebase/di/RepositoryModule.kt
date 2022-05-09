package hu.bme.aut.recipebase.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hu.bme.aut.recipebase.ui.main.MainRepository
import hu.bme.aut.recipebase.ui.recipe_details.RecipeDetailsRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideMainRepository() = MainRepository()

    @Provides
    @Singleton
    fun provideReceiptDetailsRepository() = RecipeDetailsRepository()
}