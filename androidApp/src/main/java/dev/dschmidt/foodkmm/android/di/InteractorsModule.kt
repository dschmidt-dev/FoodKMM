package dev.dschmidt.foodkmm.android.di


import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.dschmidt.foodkmm.datasource.cache.RecipeCache
import dev.dschmidt.foodkmm.datasource.network.RecipeService
import dev.dschmidt.foodkmm.interactors.recipe_details.GetRecipe
import dev.dschmidt.foodkmm.interactors.recipe_list.SearchRecipes
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object InteractorsModule {

    @Singleton
    @Provides
    fun provideSearchRecipe(
        recipeService: RecipeService,
        recipeCache: RecipeCache,
    ): SearchRecipes {
        return SearchRecipes(recipeService = recipeService, recipeCache = recipeCache)
    }

    @Singleton
    @Provides
    fun provideGetRecipe(
        recipeCache: RecipeCache,
    ): GetRecipe {
        return GetRecipe(recipeCache = recipeCache)
    }


}

