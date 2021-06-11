package dev.dschmidt.foodkmm.datasource.cache

import dev.dschmidt.foodkmm.datasource.network.RecipeServiceImpl
import dev.dschmidt.foodkmm.domain.model.Recipe
import dev.dschmidt.foodkmm.domain.util.DatetimeUtil

class RecipeCacheImpl(
    private val recipeDatabase: RecipeDatabase,
    private val datetimeUtil: DatetimeUtil
) : RecipeCache {

    private val queries: RecipeDbQueries = recipeDatabase.recipeDbQueries

    override fun insert(recipe: Recipe) {
        queries.insertRecipe(
            id = recipe.id.toLong(),
            title = recipe.title,
            publisher = recipe.publisher,
            featured_image = recipe.featuredImage,
            rating = recipe.rating.toLong(),
            source_url = recipe.sourceUrl,
            ingredients = recipe.ingredients.convertIngredientListToString(),
            date_updated = datetimeUtil.toEpochMilliseconds(recipe.dateUpdated),
            date_added = datetimeUtil.toEpochMilliseconds(recipe.dateAdded)
        )
    }

    override fun insert(recipes: List<Recipe>) {
        for (recipe in recipes) {
            insert(recipe)
        }
    }

    override fun search(query: String, page: Int): List<Recipe> {
        return queries.searchRecipes(
            query = query,
            pageSize = RecipeServiceImpl.RECIPE_PAGINATION_PAGE_SIZE.toLong(),
            offset = ((page - 1) * RecipeServiceImpl.RECIPE_PAGINATION_PAGE_SIZE).toLong()
        ).executeAsList().toRecipeList()
    }

    override fun getAll(page: Int): List<Recipe> {
        return queries.getAllRecipes(
            pageSize = RecipeServiceImpl.RECIPE_PAGINATION_PAGE_SIZE.toLong(),
            offset = ((page - 1) * RecipeServiceImpl.RECIPE_PAGINATION_PAGE_SIZE).toLong()
        ).executeAsList().toRecipeList()
    }

    override fun get(recipeId: Int): Recipe? {
        return try {
            queries.getRecipeById(recipeId.toLong()).executeAsOne().toRecipe()
        } catch (e:NullPointerException) {
            null
        }
    }
}