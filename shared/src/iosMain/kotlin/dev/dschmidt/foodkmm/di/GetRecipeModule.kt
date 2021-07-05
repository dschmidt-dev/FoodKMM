package dev.dschmidt.foodkmm.di

import dev.dschmidt.foodkmm.interactors.recipe_details.GetRecipe

class GetRecipeModule(
    private val cacheModule: CacheModule,
) {

    val getRecipe: GetRecipe by lazy {
        GetRecipe(recipeCache = cacheModule.recipeCache)
    }

}