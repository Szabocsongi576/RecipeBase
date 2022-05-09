package hu.bme.aut.recipebase.di

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, PersistenceModule::class, RepositoryModule::class])
abstract class AppComponent {}