package dev.dschmidt.foodkmm.presentation.recipe_detail

import dev.dschmidt.foodkmm.domain.model.Recipe

data class RecipeDetailState(
    val isLoading: Boolean = false,
    val recipe: Recipe? = null,
)
