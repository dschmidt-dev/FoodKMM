package dev.dschmidt.foodkmm.presentation.recipe_detail

import dev.dschmidt.foodkmm.domain.model.GenericMessageInfo
import dev.dschmidt.foodkmm.domain.model.Recipe
import dev.dschmidt.foodkmm.domain.util.Queue

data class RecipeDetailState(
    val isLoading: Boolean = false,
    val recipe: Recipe? = null,
    val queue: Queue<GenericMessageInfo> = Queue(mutableListOf()),
) {
    constructor() : this(
        isLoading = false,
        recipe = null,
        queue = Queue(mutableListOf())
    )
}
