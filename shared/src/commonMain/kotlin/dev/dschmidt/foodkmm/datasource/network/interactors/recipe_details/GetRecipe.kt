package dev.dschmidt.foodkmm.datasource.network.interactors.recipe_details

import dev.dschmidt.foodkmm.datasource.network.RecipeService
import dev.dschmidt.foodkmm.domain.model.Recipe
import dev.dschmidt.foodkmm.domain.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetRecipe(private val recipeService: RecipeService) {

    fun execute(recipeId: Int): Flow<DataState<Recipe>> = flow {
        emit(DataState.loading())
        try {
            emit(DataState.data(data = recipeService.get(recipeId)))
        } catch (e: Exception) {
            emit(DataState.error<Recipe>(e))
        }
    }
}