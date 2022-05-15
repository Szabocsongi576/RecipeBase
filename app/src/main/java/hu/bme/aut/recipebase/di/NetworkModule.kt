package hu.bme.aut.recipebase.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hu.bme.aut.recipebase.BuildConfig
import hu.bme.aut.recipebase.network.ApiClient
import hu.bme.aut.recipebase.network.api.RecipesApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(client: ApiClient): OkHttpClient {
        return client.getOkBuilder()!!.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: ApiClient): Retrofit {
        return client.getAdapterBuilder()!!.build()
    }

    @Provides
    @Singleton
    fun provideRecipesApi(client: ApiClient): RecipesApi {
        return client.createService(RecipesApi::class.java)
    }

    @Provides
    @Singleton
    fun provideApiClient(): ApiClient {
        return ApiClient(BuildConfig.AUTH_NAME, BuildConfig.API_KEY)
    }
}