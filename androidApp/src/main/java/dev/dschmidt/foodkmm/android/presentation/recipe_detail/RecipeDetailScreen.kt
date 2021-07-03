package dev.dschmidt.foodkmm.android.presentation.recipe_detail


import androidx.compose.foundation.layout.Column
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import dev.dschmidt.foodkmm.android.presentation.navigation.RecipeImage
import dev.dschmidt.foodkmm.android.presentation.recipe_detail.components.RecipeView
import dev.dschmidt.foodkmm.android.presentation.recipe_list.components.RecipeCard
import dev.dschmidt.foodkmm.android.presentation.theme.AppTheme
import dev.dschmidt.foodkmm.domain.model.Recipe
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
    AppTheme(displayProgressBar = state.isLoading) {
        if (state.recipe == null && state.isLoading) {
            //loading
        } else if (state.recipe == null) {
            Text("Unable to get the details of this recipe...")
        } else {
            RecipeView(recipe = state.recipe!!)
        }

    }
}