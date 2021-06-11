package dev.dschmidt.foodkmm.datasource.network

import dev.dschmidt.foodkmm.domain.model.Recipe

interface RecipeService {

    suspend fun search(page: Int, query: String): List<Recipe>

    suspend fun get(id: Int): Recipe

}