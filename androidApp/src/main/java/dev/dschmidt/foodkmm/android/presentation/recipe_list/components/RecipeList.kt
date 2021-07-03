package dev.dschmidt.foodkmm.android.presentation.recipe_list.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import dev.dschmidt.foodkmm.android.presentation.navigation.RECIPE_IMAGE_HEIGHT
import dev.dschmidt.foodkmm.datasource.network.RecipeServiceImpl
import dev.dschmidt.foodkmm.domain.model.Recipe

@ExperimentalMaterialApi
@Composable
fun RecipeList(
    loading: Boolean,
    recipes:List<Recipe>,
    page: Int,
    onTriggerNextPage: () -> Unit,
    onClickRecipeListItem: (Int) -> Unit,
) {
    if (loading && recipes.isEmpty()) {
        LoadingRecipeListShimmer(imageHeight = RECIPE_IMAGE_HEIGHT.dp)
    } else if (recipes.isEmpty()) {
        //nothing to show ... no recipe
    } else {
        LazyColumn {
            itemsIndexed(
                items = recipes
            ) { index, recipe ->
                if ((index + 1) >= (page * RecipeServiceImpl.RECIPE_PAGINATION_PAGE_SIZE) && !loading) {
                    onTriggerNextPage()
                }
                RecipeCard(
                    recipe = recipe,
                    onCLick = {
                        onClickRecipeListItem(recipe.id)
                    }
                )
            }
        }
    }
}