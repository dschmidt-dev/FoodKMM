package dev.dschmidt.foodkmm.android.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.dschmidt.foodkmm.datasource.network.KtorClientFactory
import dev.dschmidt.foodkmm.datasource.network.RecipeService
import dev.dschmidt.foodkmm.datasource.network.RecipeServiceImpl
import io.ktor.client.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideHttpClient(): HttpClient {
        return KtorClientFactory().build()
    }

    @Singleton
    @Provides
    fun provideRecipeService(httpClient: HttpClient): RecipeService {
        return RecipeServiceImpl(
            httpClient = httpClient,
            baseUrl = RecipeServiceImpl.BASE_URL
        )
    }

}