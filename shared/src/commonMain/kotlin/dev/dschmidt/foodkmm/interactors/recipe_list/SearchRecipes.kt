package dev.dschmidt.foodkmm.interactors.recipe_list

import dev.dschmidt.foodkmm.datasource.network.RecipeService
import dev.dschmidt.foodkmm.domain.model.Recipe
import dev.dschmidt.foodkmm.domain.util.DataState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SearchRecipes(
    private val recipeService: RecipeService,
) {
    fun execute(
        page:Int,
        query: String,
    ): Flow<DataState<List<Recipe>>> = flow {
        emit(DataState.loading<List<Recipe>>())

        //emit recipes
        try {
            val recipes = recipeService.search(
                page = page,
                query = query
            )
            delay(500)
            emit(DataState.data(data =  recipes))
        } catch (e: Exception) {
            emit(DataState.error<List<Recipe>>(e))
        }
    }
}