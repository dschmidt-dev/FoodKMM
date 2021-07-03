package dev.dschmidt.foodkmm.android.presentation.recipe_list

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.dschmidt.foodkmm.domain.model.Recipe
import dev.dschmidt.foodkmm.interactors.recipe_list.SearchRecipes
import dev.dschmidt.foodkmm.presentation.recipe_list.FoodCategory
import dev.dschmidt.foodkmm.presentation.recipe_list.RecipeListEvents
import dev.dschmidt.foodkmm.presentation.recipe_list.RecipeListState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class RecipeListViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle, //don't need for this VM
    private val searchRecipes: SearchRecipes,
): ViewModel() {

    val state: MutableState<RecipeListState> = mutableStateOf(RecipeListState())

    init {
        loadRecipes()
    }

    fun onTriggerEvent(event: RecipeListEvents) {
        when (event) {
            RecipeListEvents.LoadRecipes -> loadRecipes()
            RecipeListEvents.NextPage -> nextPage()
            RecipeListEvents.NewSearch -> newSearch()
            is RecipeListEvents.OnUpdateQuery -> state.value = state.value.copy(query = event.query, selectedCategory = null)
            is RecipeListEvents.OnSelectCategory -> onSelectCategory(event.category)
            else -> handleError("Invalid event")
        }
    }

    private fun onSelectCategory(category: FoodCategory) {
        state.value = state.value.copy(selectedCategory =category, query = category.value)
        newSearch()
    }

    private fun newSearch() {
        state.value = state.value.copy(page = 1, recipes = listOf())
        loadRecipes()
    }

    private fun nextPage() {
        state.value = state.value.copy(page = state.value.page + 1)
        loadRecipes()
    }

    private fun loadRecipes() {
        searchRecipes.execute(
            page = state.value.page,
            query = state.value.query
        ).onEach { dataState ->
            state.value = state.value.copy(isLoading = dataState.isLoading)

            dataState.data?.let { recipes ->
                appendRecipes(recipes)
            }

            dataState.message?.let { msg ->
                handleError(msg)

            }
        }.launchIn(viewModelScope)
    }

    private fun appendRecipes(recipes: List<Recipe>) {
        val curr = ArrayList(state.value.recipes)
        curr.addAll(recipes)
        state.value = state.value.copy(recipes = curr)
    }

    private fun handleError(errorMessage: String) {
        println("RecipeListVM : ${errorMessage}")
    }
}