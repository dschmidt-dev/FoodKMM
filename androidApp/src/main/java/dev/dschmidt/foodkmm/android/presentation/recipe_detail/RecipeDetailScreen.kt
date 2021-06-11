package dev.dschmidt.foodkmm.android.presentation.recipe_detail


import androidx.compose.foundation.layout.Column
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import dev.dschmidt.foodkmm.android.presentation.theme.AppTheme
import dev.dschmidt.foodkmm.domain.model.Recipe

@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@Composable
fun RecipeDetailScreen(
    recipe: Recipe?
) {
    AppTheme(displayProgressBar = false) {
        if (recipe == null) {
            Text("Unable to get the details of this recipe...")
        } else {
            Column {
                Text("RecipeDetailScreen: ${recipe.title} with text: ${recipe.ingredients}")
            }
        }

    }
}