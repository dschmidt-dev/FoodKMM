package dev.dschmidt.foodkmm.android.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.dschmidt.foodkmm.android.BaseApplication
import dev.dschmidt.foodkmm.datasource.cache.*
import dev.dschmidt.foodkmm.domain.util.DatetimeUtil
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CacheModule {

    @Singleton
    @Provides
    fun provideRecipeDatabase(context: BaseApplication): RecipeDatabase {
        return RecipeDatabaseFactory(driverFactory = DriverFactory(context)).createDatabase()
    }

    @Singleton
    @Provides
    fun provideRecipeCache(recipeDatabase: RecipeDatabase): RecipeCache {
        return RecipeCacheImpl(recipeDatabase = recipeDatabase, datetimeUtil = DatetimeUtil())
    }

}