package dev.dschmidt.foodkmm.di

import dev.dschmidt.foodkmm.datasource.cache.*
import dev.dschmidt.foodkmm.domain.util.DatetimeUtil

class CacheModule {

    private val driverFactory: DriverFactory by lazy { DriverFactory() }

    val recipeDatabase: RecipeDatabase by lazy {
        RecipeDatabaseFactory(driverFactory = driverFactory).createDatabase()
    }

    val recipeCache: RecipeCache by lazy {
        RecipeCacheImpl(
            recipeDatabase = recipeDatabase,
            datetimeUtil = DatetimeUtil(),
        )
    }

}