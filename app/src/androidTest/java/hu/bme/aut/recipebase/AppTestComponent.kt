package hu.bme.aut.recipebase

import dagger.Component
import hu.bme.aut.recipebase.di.AppComponent
import hu.bme.aut.recipebase.di.NetworkModule
import hu.bme.aut.recipebase.di.PersistenceModule
import hu.bme.aut.recipebase.di.RepositoryModule
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, PersistenceModule::class, RepositoryModule::class])
abstract class AppTestComponent : AppComponent() {}
