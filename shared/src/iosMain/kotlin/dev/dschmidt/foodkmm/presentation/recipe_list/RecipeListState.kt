package dev.dschmidt.foodkmm.presentation.recipe_list

import dev.dschmidt.foodkmm.domain.model.GenericMessageInfo
import dev.dschmidt.foodkmm.domain.model.Recipe
import dev.dschmidt.foodkmm.domain.util.Queue

actual data class RecipeListState(
    val isLoading: Boolean = false,
    val page: Int = 1,
    val query: String = "",
    val selectedCategory: FoodCategory? = null,
    val recipes: List<Recipe> = listOf(),
    val bottomRecipe: Recipe? = null,
    val queue: Queue<GenericMessageInfo> = Queue(mutableListOf()),
) {

    //secondary constructor for swift ui
    constructor() : this(
        isLoading = false,
        page = 1,
        query = "",
        selectedCategory = null,
        recipes = listOf(),
        bottomRecipe = null,
        queue = Queue(mutableListOf())
    )

    companion object {
        const val RECIPE_PAGINATION_PAGE_SIZE = 30
    }

}