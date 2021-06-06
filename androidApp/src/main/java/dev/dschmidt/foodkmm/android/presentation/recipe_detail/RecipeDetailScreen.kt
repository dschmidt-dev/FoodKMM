package dev.dschmidt.foodkmm.android.presentation.recipe_detail


import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun RecipeDetailScreen(
    recipeId: Int?,
    dummy: String?
){
    if(recipeId == null){
        Text("Unable to get the details of this recipe...")
    }
    else{
        Column{
            Text("RecipeDetailScreen: $recipeId with text: $dummy")
        }
    }
}