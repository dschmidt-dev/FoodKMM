package dev.dschmidt.foodkmm.di

import dev.dschmidt.foodkmm.datasource.network.KtorClientFactory
import dev.dschmidt.foodkmm.datasource.network.RecipeService
import dev.dschmidt.foodkmm.datasource.network.RecipeServiceImpl

class NetworkModule {

    val recipeService: RecipeService by lazy {
        RecipeServiceImpl(
            httpClient = KtorClientFactory().build(),
            baseUrl = RecipeServiceImpl.BASE_URL
        )
    }

}