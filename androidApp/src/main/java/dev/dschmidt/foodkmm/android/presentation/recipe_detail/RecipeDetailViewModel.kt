package dev.dschmidt.foodkmm.android.presentation.recipe_detail

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.dschmidt.foodkmm.interactors.recipe_details.GetRecipe
import dev.dschmidt.foodkmm.domain.model.Recipe
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@ExperimentalStdlibApi
@HiltViewModel
class RecipeDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getRecipe: GetRecipe): ViewModel() {

    val recipe: MutableState<Recipe?> = mutableStateOf(null)

    init {
        savedStateHandle.get<Int>("recipeId")?.let { recipeId ->
            getRecipe(recipeId = recipeId)
        }
    }

    private fun getRecipe(recipeId: Int) {
        getRecipe.execute(recipeId)
            .onEach { dataState ->
                println("RecipeDetailVM : ${dataState.isLoading}")
                dataState.data?.let { recipe ->
                    println("RecipeDetailVM : ${recipe}")
                    this.recipe.value = recipe

                }
                dataState.message?.let { msg ->
                    println("RecipeDetailVM : ${msg}")
                }
            }
            .launchIn(viewModelScope)
    }
}