package dev.dschmidt.foodkmm.android.di


import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
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
    ): SearchRecipes {
        return SearchRecipes(recipeService = recipeService)
    }

    @Singleton
    @Provides
    fun provideGetRecipe(
        recipeService: RecipeService,
    ): GetRecipe {
        return GetRecipe(recipeService = recipeService)
    }


}

