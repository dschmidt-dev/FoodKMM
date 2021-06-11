package dev.dschmidt.foodkmm.android.presentation.recipe_detail

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.dschmidt.foodkmm.datasource.network.RecipeService
import dev.dschmidt.foodkmm.domain.model.Recipe
import dev.dschmidt.foodkmm.domain.util.DatetimeUtil
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalStdlibApi
@HiltViewModel
class RecipeDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val recipeService: RecipeService): ViewModel() {

    val recipe: MutableState<Recipe?> = mutableStateOf(null)

    init {
        savedStateHandle.get<Int>("recipeId")?.let { recipeId ->
            viewModelScope.launch {
                recipe.value = recipeService.get(recipeId)
                println("KtorTest: ${recipe.value!!.title}")
                println("KtorTest: ${recipe.value!!.ingredients}")
                println("KtorTest: ${DatetimeUtil().humanizeDatetime(recipe.value!!.dateUpdated)}")

            }
        }
    }

}