package dev.dschmidt.foodkmm.interactors.recipe_details

import dev.dschmidt.foodkmm.datasource.cache.RecipeCache
import dev.dschmidt.foodkmm.datasource.network.RecipeService
import dev.dschmidt.foodkmm.domain.model.GenericMessageInfo
import dev.dschmidt.foodkmm.domain.model.Recipe
import dev.dschmidt.foodkmm.domain.model.UIComponentType
import dev.dschmidt.foodkmm.domain.util.CommonFlow
import dev.dschmidt.foodkmm.domain.util.DataState
import dev.dschmidt.foodkmm.domain.util.asCommonFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetRecipe(private val recipeCache: RecipeCache, ){

    fun execute(recipeId: Int): CommonFlow<DataState<Recipe>> = flow {
        try {

            emit(DataState.loading())

            if (recipeId == 1 || recipeId == 5) {
                throw Exception("Invalid RecipeID: $recipeId")
            }

            kotlinx.coroutines.delay(2000)

            emit(DataState.data(data = recipeCache.get(recipeId)))
        } catch (e: Exception) {
            emit(DataState.error<Recipe>(
                GenericMessageInfo
                    .Builder()
                    .id("SearchRecipes.Error")
                    .title("Error")
                    .uiComponentType(UIComponentType.Dialog)
                    .description(e.message ?: "Unknown Error")
                    .throwable(e)
                    .build())
            )
        }
    }.asCommonFlow()
}