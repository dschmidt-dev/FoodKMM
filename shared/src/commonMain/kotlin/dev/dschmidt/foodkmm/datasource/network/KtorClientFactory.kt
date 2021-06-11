package dev.dschmidt.foodkmm.datasource.network

import dev.dschmidt.foodkmm.datasource.network.model.RecipeDto
import dev.dschmidt.foodkmm.domain.model.Recipe
import dev.dschmidt.foodkmm.domain.util.DatetimeUtil
import io.ktor.client.*

expect class KtorClientFactory() {

    fun build(): HttpClient

}
//TOKEN 9c8b06d329136da358c2d00e76946b0111ce2c48

fun RecipeDto.toRecipe(): Recipe {
    val dateTimeUtil = DatetimeUtil()
    return Recipe(
        id = pk,
        title = title,
        featuredImage = featuredImage,
        rating = rating,
        publisher = publisher,
        sourceUrl = sourceUrl,
        ingredients = ingredients,
        dateAdded = dateTimeUtil.toLocalDate(longDateAdded.toDouble()),
        dateUpdated = dateTimeUtil.toLocalDate(longDateUpdated.toDouble()),
    )
}

fun List<RecipeDto>.toRecipeList(): List<Recipe> {
    return map { it.toRecipe() }
}