package dev.dschmidt.foodkmm.di

import dev.dschmidt.foodkmm.interactors.recipe_list.SearchRecipes

class SearchRecipesModule(
    val networkModule: NetworkModule,
    val cacheModule: CacheModule
) {
    val searchRecipes: SearchRecipes by lazy {
        SearchRecipes(
            recipeService = networkModule.recipeService,
            recipeCache = cacheModule.recipeCache,
        )
    }
}