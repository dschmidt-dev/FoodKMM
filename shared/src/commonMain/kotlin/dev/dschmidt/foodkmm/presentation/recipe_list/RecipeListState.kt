package dev.dschmidt.foodkmm.presentation.recipe_list

import dev.dschmidt.foodkmm.domain.model.Recipe

data class RecipeListState(
    val isLoading: Boolean = false,
    val page: Int = 1,
    val query: String = "",
    val recipes: List<Recipe> = listOf(),
)