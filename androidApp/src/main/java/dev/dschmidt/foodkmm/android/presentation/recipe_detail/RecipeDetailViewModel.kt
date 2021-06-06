package dev.dschmidt.foodkmm.android.presentation.recipe_detail

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.dschmidt.foodkmm.android.di.Dummy
import javax.inject.Inject

@HiltViewModel
class RecipeDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val dummyRepo: Dummy
): ViewModel() {

    val recipeId: MutableState<Int?> = mutableStateOf(null)
    val dummy: MutableState<String?> = mutableStateOf(null)

    init {
        savedStateHandle.get<Int>("recipeId")?.let { recipeId ->
            this.recipeId.value = recipeId
        }
        dummy.value = dummyRepo.description()
    }

}