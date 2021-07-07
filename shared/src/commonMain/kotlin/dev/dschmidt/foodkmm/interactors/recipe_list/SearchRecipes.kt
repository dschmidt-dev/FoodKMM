package dev.dschmidt.foodkmm.interactors.recipe_list

import dev.dschmidt.foodkmm.datasource.cache.RecipeCache
import dev.dschmidt.foodkmm.datasource.network.RecipeService
import dev.dschmidt.foodkmm.domain.model.GenericMessageInfo
import dev.dschmidt.foodkmm.domain.model.Recipe
import dev.dschmidt.foodkmm.domain.model.UIComponentType
import dev.dschmidt.foodkmm.domain.util.CommonFlow
import dev.dschmidt.foodkmm.domain.util.DataState
import dev.dschmidt.foodkmm.domain.util.asCommonFlow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SearchRecipes(
    private val recipeService: RecipeService,
    private val recipeCache: RecipeCache,
) {
    fun execute(
        page:Int,
        query: String,
    ): CommonFlow<DataState<List<Recipe>>> = flow {
        emit(DataState.loading<List<Recipe>>())

        //emit recipes
        try {
            val recipes = recipeService.search(
                page = page,
                query = query
            )
            delay(500)

            if (query == "error") {
                throw Exception("Forcing an Error ... Search Failed")
            }

            recipeCache.insert(recipes)

            val cacheResult = if (query.isBlank()) {
                recipeCache.getAll(page = page)
            } else {
                recipeCache.search(
                    query = query,
                    page = page
                )
            }
            emit(DataState.data(message = null, data = cacheResult))
        } catch (e: Exception) {
            emit(DataState.error<List<Recipe>>(
                GenericMessageInfo
                    .Builder()
                    .id("SearchRecipes.Error")
                    .title("Error")
                    .uiComponentType(UIComponentType.Dialog)
                    .description(e.message ?: "Unknown Error")
                    .throwable(e)
                    .build()))
        }
    }.asCommonFlow()
}