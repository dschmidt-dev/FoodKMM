package dev.dschmidt.foodkmm.android.presentation.recipe_detail

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.dschmidt.foodkmm.domain.model.GenericMessageInfo
import dev.dschmidt.foodkmm.interactors.recipe_details.GetRecipe
import dev.dschmidt.foodkmm.domain.model.Recipe
import dev.dschmidt.foodkmm.domain.model.UIComponentType
import dev.dschmidt.foodkmm.domain.util.GenericMessageInfoQueueUtil
import dev.dschmidt.foodkmm.domain.util.Queue
import dev.dschmidt.foodkmm.presentation.recipe_detail.RecipeDetailEvents
import dev.dschmidt.foodkmm.presentation.recipe_detail.RecipeDetailState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.util.*
import javax.inject.Inject

@ExperimentalStdlibApi
@HiltViewModel
class RecipeDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getRecipe: GetRecipe): ViewModel() {

    val state: MutableState<RecipeDetailState> = mutableStateOf(RecipeDetailState())

    init {
        savedStateHandle.get<Int>("recipeId")?.let { recipeId ->
            onTriggerEvent(RecipeDetailEvents.GetRecipe(recipeId = recipeId))
        }
    }

    fun onTriggerEvent(event: RecipeDetailEvents) {
        when (event) {
            is RecipeDetailEvents.GetRecipe -> getRecipe(event.recipeId)
            RecipeDetailEvents.OnRemoveHeadMessageFromQueue -> removeHeadMessage()
            else -> appendToMessageQueue(GenericMessageInfo
                .Builder()
                .id(UUID.randomUUID().toString())
                .title("Error")
                .uiComponentType(UIComponentType.Dialog)
                .description("Wrong Event")
                .build())
        }
    }

    private fun getRecipe(recipeId: Int) {
        getRecipe.execute(recipeId)
            .onEach { dataState ->
                state.value = state.value.copy(isLoading = dataState.isLoading)

                dataState.data?.let { recipe ->
                    state.value = state.value.copy(recipe = recipe)
                }

                dataState.message?.let { msg ->
                    appendToMessageQueue(msg)
                }
            }
            .launchIn(viewModelScope)
    }

    private fun appendToMessageQueue(messageInfo: GenericMessageInfo) {
        if (!GenericMessageInfoQueueUtil().doesMessageAlreadyExistInQueue(
                queue = state.value.queue,
                messageInfo = messageInfo
            )) {
            val queue = state.value.queue
            queue.add(messageInfo)
            state.value = state.value.copy(queue = queue)
        }
    }

    private fun removeHeadMessage() {
        try {
            val queue = state.value.queue
            queue.remove()
            state.value = state.value.copy(queue = Queue(mutableListOf())) //force recompose, because changes inside a list doesn't trigger a recompose
            state.value = state.value.copy(queue = queue)
        } catch (e: Exception) {

        }
    }

}