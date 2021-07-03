package dev.dschmidt.foodkmm.presentation.recipe_list

sealed class RecipeListEvents {

    object LoadRecipes: RecipeListEvents()

    object NextPage: RecipeListEvents()

}