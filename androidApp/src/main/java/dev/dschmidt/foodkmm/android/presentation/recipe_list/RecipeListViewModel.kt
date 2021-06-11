package dev.dschmidt.foodkmm.android.presentation.recipe_list

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.dschmidt.foodkmm.datasource.network.interactors.recipe_list.SearchRecipes
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class RecipeListViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle, //don't need for this VM
    private val searchRecipes: SearchRecipes,
): ViewModel() {

    init {
        loadRecipes()
    }

    private fun loadRecipes() {
        searchRecipes.execute(
            page =1,
            query = "chicken"
        ).onEach { dataState ->
            println("RecipeListVM : ${dataState.isLoading}")
            dataState.data?.let { recipes ->
                println("RecipeListVM : ${recipes}")
            }
            dataState.message?.let { msg ->
                println("RecipeListVM : ${msg}")
            }
        }.launchIn(viewModelScope)
    }

}