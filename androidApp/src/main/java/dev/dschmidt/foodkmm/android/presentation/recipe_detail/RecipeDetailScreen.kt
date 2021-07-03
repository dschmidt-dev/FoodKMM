package dev.dschmidt.foodkmm.android.presentation.recipe_detail


import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.unit.dp
import dev.dschmidt.foodkmm.android.presentation.navigation.RECIPE_IMAGE_HEIGHT
import dev.dschmidt.foodkmm.android.presentation.recipe_detail.components.LoadingRecipeShimmer
import dev.dschmidt.foodkmm.android.presentation.recipe_detail.components.RecipeView
import dev.dschmidt.foodkmm.android.presentation.theme.AppTheme
import dev.dschmidt.foodkmm.presentation.recipe_detail.RecipeDetailEvents
import dev.dschmidt.foodkmm.presentation.recipe_detail.RecipeDetailState

@ExperimentalStdlibApi
@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@Composable
fun RecipeDetailScreen(
    state: RecipeDetailState,
    onTriggerEvent: (RecipeDetailEvents) -> Unit,
) {
    AppTheme(
        displayProgressBar = state.isLoading,
        dialogQueue = state.queue,
        onRemoveHeadMessageFromQueue = {
            onTriggerEvent(RecipeDetailEvents.OnRemoveHeadMessageFromQueue)
        },
    ) {
        if (state.recipe == null && state.isLoading) {
            LoadingRecipeShimmer(imageHeight = RECIPE_IMAGE_HEIGHT.dp)
        } else if (state.recipe == null) {
            Text("Unable to get the details of this recipe...")
        } else {
            RecipeView(recipe = state.recipe!!)
        }

    }
}